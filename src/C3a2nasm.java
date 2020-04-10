import c3a.*;
import nasm.*;
import ts.Ts;
import ts.TsItemFct;
import ts.TsItemVar;

public class C3a2nasm implements C3aVisitor <NasmOperand> {

    private Nasm nasm;
    private C3a c3a;
    private Ts table;
    private TsItemFct foncCourante;

    public C3a2nasm(C3a c3a, Ts ts) {
        this.nasm = new Nasm(ts);
        this.c3a = c3a;
        this.table = ts;

        this.prefixe();
        for (int i=0; this.c3a.listeInst.size() > i; i++) {
            this.c3a.listeInst.get(i).accept(this);
        }
    }

    public Nasm getNasm() {
        return nasm;
    }

    public void prefixe() {
        NasmLabel nasmLabel = new NasmLabel("main");
        this.foncCourante = table.getFct("main");
        NasmCall nasmCall = new NasmCall(null, nasmLabel, "");
        nasm.ajouteInst(nasmCall);

        NasmRegister nasmRegister = nasm.newRegister();
        nasmRegister.colorRegister(Nasm.REG_EBX);
        NasmMov nasmMov = new NasmMov(null, nasmRegister, new NasmConstant(0), " valeur de retour du programme");
        nasm.ajouteInst(nasmMov);

        NasmRegister nasmRegister2 = nasm.newRegister();
        nasmRegister2.colorRegister(Nasm.REG_EAX);
        NasmMov nasmMov2 = new NasmMov(null, nasmRegister2, new NasmConstant(1), "");
        nasm.ajouteInst(nasmMov2);

        NasmInt nasmInt = new NasmInt(null, "");
        nasm.ajouteInst(nasmInt);
    }

    @Override
    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstCall inst) {
        NasmRegister nasmRegister = new NasmRegister(Nasm.REG_ESP);
        nasmRegister.colorRegister(Nasm.REG_ESP);

        NasmConstant nasmConstant = new NasmConstant(4);

        NasmSub nasmSub = new NasmSub(null, nasmRegister, nasmConstant, "allocation mémoire pour la valeur de retour");
        nasm.ajouteInst(nasmSub);

        NasmLabel nasmOperand = (NasmLabel) inst.op1.accept(this);
        NasmCall nasmCall = new NasmCall(null, nasmOperand, "");
        nasm.ajouteInst(nasmCall);

        if (inst.result != null) {
            NasmOperand nasmOperand1 = inst.result.accept(this);
            NasmPop nasmPop = new NasmPop(null,nasmOperand1 , "récupération de la valeur de retour");
            nasm.ajouteInst(nasmPop);
        }
        if (table.getFct(nasmOperand.val).nbArgs > 0) {
            NasmConstant nasmConstant1 = new NasmConstant(inst.op1.val.nbArgs * 4);
            NasmAdd nasmAdd = new NasmAdd(null, nasmRegister, nasmConstant1, "désallocation des arguments");
            nasm.ajouteInst(nasmAdd);
        }
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        NasmOperand label = new NasmLabel(inst.val.identif);

        NasmRegister nasmRegisterEBP = new NasmRegister(Nasm.REG_EBP);
        nasmRegisterEBP.colorRegister(Nasm.REG_EBP); // EBP

        NasmRegister nasmRegisterESP = new NasmRegister(Nasm.REG_ESP);
        nasmRegisterESP.colorRegister(Nasm.REG_ESP); // ESP

        NasmPush nasmPush = new NasmPush(label, nasmRegisterEBP,"sauvegarde la valeur de ebp");
        nasm.ajouteInst(nasmPush);

        NasmMov nasmMov = new NasmMov(null, nasmRegisterEBP,nasmRegisterESP, "nouvelle valeur de ebp");
        nasm.ajouteInst(nasmMov);

        this.foncCourante = inst.val;

        int nbVarLocal = 0;
        for (TsItemVar tsItemVar : inst.val.getTable().variables.values()) {
            nbVarLocal += 4;
        }

        NasmConstant nasmConstant = new NasmConstant(nbVarLocal);
        NasmSub nasmSub = new NasmSub(null, nasmRegisterESP, nasmConstant, "allocation des variables locales");
        nasm.ajouteInst(nasmSub);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInst inst) {
        /* Jamais utilisé */
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        if (oper instanceof NasmConstant) {
            NasmRegister nasmRegister = nasm.newRegister();
            NasmMov nasmMov = new NasmMov(null, nasmRegister, oper, "JumpIfLess 1");
            nasm.ajouteInst(nasmMov);

            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "on passe par un registre temporaire");
            nasm.ajouteInst(nasmCmp);
        } else {
            NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "JumpIfLess 1");
            nasm.ajouteInst(nasmCmp);
        }

        NasmJl nasmJl = new NasmJl(null, result, "JumpIfLess 2");
        nasm.ajouteInst(nasmJl);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstMult inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmMul(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstRead inst) {
        /* Jamais utilisé */
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstSub inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmSub(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstAffect inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper = inst.op1.accept(this);
        NasmOperand dest = inst.result.accept(this);
        NasmMov nasmMov = new NasmMov(label, dest, oper, "Affect");
        nasm.ajouteInst(nasmMov);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);

        NasmOperand dest = inst.result.accept(this);

        NasmRegister nasmRegister = nasm.newRegister();
        nasmRegister.colorRegister(Nasm.REG_EAX);

        NasmRegister nasmRegister1= nasm.newRegister();

        nasm.ajouteInst(new NasmMov(label, nasmRegister, oper1, ""));
        nasm.ajouteInst(new NasmMov(null, nasmRegister1, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, nasmRegister1, ""));

        NasmRegister nasmRegister2 = nasm.newRegister();
        nasmRegister2.colorRegister(Nasm.REG_EAX);

        nasm.ajouteInst(new NasmMov(null, dest, nasmRegister2, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmRegister nasmRegisterESP = new NasmRegister(Nasm.REG_ESP);
        nasmRegisterESP.colorRegister(Nasm.REG_ESP);

        int espaceVarLocal = table.getFct(foncCourante.identif).getTable().variables.size()*4;
        NasmConstant nasmConstant = new NasmConstant(espaceVarLocal);

        NasmAdd nasmAdd = new NasmAdd(label, nasmRegisterESP, nasmConstant, "désallocation des variables locales");
        nasm.ajouteInst(nasmAdd);

        NasmRegister nasmRegisterEBP = new NasmRegister(Nasm.REG_EBP);
        nasmRegisterEBP.colorRegister(Nasm.REG_EBP);
        NasmPop nasmPop = new NasmPop(null, nasmRegisterEBP, "restaure la valeur de ebp");
        nasm.ajouteInst(nasmPop);

        NasmRet nasmRet = new NasmRet(null, "");
        nasm.ajouteInst(nasmRet);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        //Si la premiere valeur n'est par une variable, on passe par une variable
        if (oper instanceof NasmConstant) {

            NasmRegister nasmRegister = nasm.newRegister();
            NasmMov nasmMov = new NasmMov(label, nasmRegister, oper, "JumpIfEqual 1");
            nasm.ajouteInst(nasmMov);

            NasmCmp nasmCmp = new NasmCmp(null, nasmRegister, oper2, "on passe par un registre temporaire");
            nasm.ajouteInst(nasmCmp);
        } else {
            NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "JumpIfEqual 1");
            nasm.ajouteInst(nasmCmp);
        }
        NasmJe nasmJe = new NasmJe(null, result, "JumpIfEqual 2");
        nasm.ajouteInst(nasmJe);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        if (oper instanceof NasmConstant) {
            NasmRegister nasmRegister = nasm.newRegister();
            NasmMov nasmMov = new NasmMov(null, nasmRegister, oper, "jumpIfNotEqual 1");
            nasm.ajouteInst(nasmMov);

            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "on passe par un registre temporaire");
            nasm.ajouteInst(nasmCmp);
        } else {
            NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "jumpIfNotEqual 1");
            nasm.ajouteInst(nasmCmp);
        }

        NasmJne nasmJne = new NasmJne(null, result, "jumpIfNotEqual 2");
        nasm.ajouteInst(nasmJne);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJump inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand result = inst.result.accept(this);
        NasmJmp nasmJmp = new NasmJmp(label, result, "Jump");
        nasm.ajouteInst(nasmJmp);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand nasmOperand = inst.op1.accept(this);
        NasmPush nasmPush = new NasmPush(null, nasmOperand,"Param");
        nasm.ajouteInst(nasmPush);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        NasmOperand nasmOperand = inst.op1.accept(this);
        NasmRegister nasmRegister = new NasmRegister(Nasm.REG_EBP);
        nasmRegister.colorRegister(Nasm.REG_EBP);
        NasmAddress nasmAddress = new NasmAddress(nasmRegister, '+',new NasmConstant(2));
        NasmMov nasmMov = new NasmMov(null,nasmAddress , nasmOperand, "ecriture de la valeur de retour");
        nasm.ajouteInst(nasmMov);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmRegister nasmRegister = nasm.newRegister();
        nasmRegister.colorRegister(Nasm.REG_EAX);
        NasmOperand nasmOperand = inst.op1.accept(this);

        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmMov nasmMov = new NasmMov(label, nasmRegister, nasmOperand, "Write 1");
        nasm.ajouteInst(nasmMov);

        NasmLabel nasmLabel = new NasmLabel("iprintLF");
        NasmCall nasmCall = new NasmCall(null, nasmLabel, "Write 2");
        nasm.ajouteInst(nasmCall);
        return null;
    }

    @Override
    public NasmOperand visit(C3aConstant oper) {
        return new NasmConstant(oper.val);
    }

    @Override
    public NasmOperand visit(C3aLabel oper) {
        return new NasmLabel(oper.toString());
    }

    @Override
    public NasmOperand visit(C3aTemp oper) {
        NasmRegister nasmRegister = nasm.newRegister();
        nasmRegister.val = oper.num;
        return nasmRegister;
    }

    @Override
    public NasmOperand visit(C3aVar oper) {
        // Si variable globale
        if (table.getVar(oper.item.identif) != null) {
            if (oper.index != null) {
                NasmLabel nasmLabel = new NasmLabel(oper.item.identif);
                NasmOperand nasmOperand = oper.index.accept(this);
                return new NasmAddress(nasmLabel, '+', nasmOperand);
            }
            NasmLabel nasmLabel = new NasmLabel(oper.item.identif);
            return new NasmAddress(nasmLabel);
        }
        //si paramètre
        if (table.getFct(this.foncCourante.identif).getTable().getVar(oper.item.identif).isParam) {
            NasmRegister nasmRegister = new NasmRegister(Nasm.REG_EBP);
            nasmRegister.colorRegister(Nasm.REG_EBP);
            int base = 2 + table.getFct(this.foncCourante.identif).getNbArgs() - oper.item.adresse;
            return new NasmAddress(nasmRegister, '+', new NasmConstant(base));
        }
        //si variable local
        if (table.getFct(this.foncCourante.identif).getTable().getVar(oper.item.identif) != null) {
            NasmRegister nasmRegister = new NasmRegister(Nasm.REG_EBP);
            nasmRegister.colorRegister(Nasm.REG_EBP);
            int base = oper.item.adresse + oper.item.taille;
            return new NasmAddress(nasmRegister, '-', new NasmConstant(base));
        }
        return null;
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel(oper.val.identif);
    }
}
