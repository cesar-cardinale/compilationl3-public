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
        System.out.println("Add");
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        if (dest instanceof NasmRegister) ((NasmRegister) dest).colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstCall inst) {
        System.out.println("Call");
        NasmRegister nasmRegister = new NasmRegister(Nasm.REG_ESP);
        nasmRegister.colorRegister(Nasm.REG_ESP);

        NasmConstant nasmConstant = new NasmConstant(4);

        NasmSub nasmSub = new NasmSub(null, nasmRegister, nasmConstant, "allocation mémoire pour la valeur de retour");
        nasm.ajouteInst(nasmSub);

        NasmOperand nasmOperand = inst.op1.accept(this);
        NasmCall nasmCall = new NasmCall(null, nasmOperand, "");
        nasm.ajouteInst(nasmCall);

        if (inst.result != null) {
            NasmOperand nasmOperand1 = inst.result.accept(this);
            NasmPop nasmPop = new NasmPop(null,nasmOperand1 , "récupération de la valeur de retour");
            nasm.ajouteInst(nasmPop);
        }
        if (table.getFct(foncCourante.identif).nbArgs > 0) {
            NasmConstant nasmConstant1 = new NasmConstant(inst.op1.val.nbArgs * 4);
            NasmAdd nasmAdd = new NasmAdd(null, nasmRegister, nasmConstant1, "désallocation des arguments");
            nasm.ajouteInst(nasmAdd);
        }
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        System.out.println("Begin");
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
        System.out.println("Inst");
        /* Jamais utilisé */
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        System.out.println("Less");
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        if (oper instanceof NasmConstant) {
            NasmRegister nasmRegister = nasm.newRegister();
            nasmRegister.colorRegister(Nasm.REG_EAX);
            NasmMov nasmMov = new NasmMov(null, nasmRegister, oper, "JumpIfLess 1");
            nasm.ajouteInst(nasmMov);

            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "on passe par un registre temporaire");
            nasm.ajouteInst(nasmCmp);
        } else {
            NasmRegister nasmRegister = nasm.newRegister();
            nasmRegister.colorRegister(Nasm.REG_EBX);
            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "JumpIfLess 1");
            nasm.ajouteInst(nasmCmp);
        }

        NasmJl nasmJl = new NasmJl(null, result, "JumpIfLess 2");
        nasm.ajouteInst(nasmJl);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstMult inst) {
        System.out.println("Mult");
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
        System.out.println("InstRead");
        /* Jamais utilisé */
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstSub inst) {
        System.out.println("Sub");
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
        System.out.println("Affect");
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper = inst.op1.accept(this);
        NasmOperand dest = inst.result.accept(this);
        if (dest instanceof NasmRegister) {
            ((NasmRegister) dest).colorRegister(Nasm.REG_EAX);
        }
        System.out.println(dest.getClass());
        NasmMov nasmMov = new NasmMov(label, dest, oper, "Affect");
        nasm.ajouteInst(nasmMov);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        System.out.println("Div");
        //Pas sur

        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);

        NasmOperand dest = inst.result.accept(this);

        NasmRegister nasmRegisterEBX = new NasmRegister(Nasm.REG_EBX);
        nasmRegisterEBX.colorRegister(Nasm.REG_EBX);

        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmMov(null, nasmRegisterEBX, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, nasmRegisterEBX, ""));

        NasmRegister nasmRegister = new NasmRegister(Nasm.REG_EAX);
        nasmRegister.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmMov(null, nasmRegister, dest, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        System.out.println("End");
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
        System.out.println("InstJumpIfEqual");
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        //Si la premiere valeur n'est par une variable, on passe par une variable
        if (oper instanceof NasmConstant) {

            NasmRegister nasmRegister = nasm.newRegister();
            nasmRegister.colorRegister(Nasm.REG_EBX);
            System.out.println("num : "+nasm.getTempCounter());
            NasmMov nasmMov = new NasmMov(label, nasmRegister, oper, "JumpIfEqual 1");
            nasm.ajouteInst(nasmMov);

            NasmCmp nasmCmp = new NasmCmp(null, nasmRegister, oper2, "on passe par un registre temporaire");
            nasm.ajouteInst(nasmCmp);
        } else {
            NasmRegister nasmRegister = nasm.newRegister();
            nasmRegister.colorRegister(Nasm.REG_EBX);
            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "JumpIfEqual 1");
            nasm.ajouteInst(nasmCmp);
        }
        NasmJe nasmJe = new NasmJe(null, result, "JumpIfEqual 2");
        nasm.ajouteInst(nasmJe);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        System.out.println("Not");
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        if (oper instanceof NasmConstant) {
            NasmRegister nasmRegister = nasm.newRegister();
            nasmRegister.colorRegister(Nasm.REG_EBX);
            NasmMov nasmMov = new NasmMov(null, nasmRegister, oper, "JumpIfNotEqual 1");
            nasm.ajouteInst(nasmMov);

            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "on passe par un registre temporaire");
            nasm.ajouteInst(nasmCmp);
        } else {
            NasmRegister nasmRegister = nasm.newRegister();
            nasmRegister.colorRegister(Nasm.REG_EBX);
            NasmCmp nasmCmp = new NasmCmp(label, nasmRegister, oper2, "JumpIfNotEqual 1");
            nasm.ajouteInst(nasmCmp);
        }

        NasmJne nasmJne = new NasmJne(null, result, "JumpIfNotEqual 2");
        nasm.ajouteInst(nasmJne);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJump inst) {
        System.out.println("Jump");
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand result = inst.result.accept(this);
        NasmJmp nasmJmp = new NasmJmp(label, result, "Jump");
        nasm.ajouteInst(nasmJmp);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstParam inst) {
        System.out.println("Param");
        NasmOperand nasmOperand = inst.op1.accept(this);
        NasmPush nasmPush = new NasmPush(null, nasmOperand,"Param");
        nasm.ajouteInst(nasmPush);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        System.out.println("Return");
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
        System.out.println("Write");
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
        System.out.println("C3aConstant");
        return new NasmConstant(oper.val);
    }

    @Override
    public NasmOperand visit(C3aLabel oper) {
        System.out.println("C3aLabel");
        return new NasmLabel(oper.toString());
    }

    @Override
    public NasmOperand visit(C3aTemp oper) {
        System.out.println("C3aTemp");
        NasmRegister nasmRegister = nasm.newRegister();
        nasmRegister.colorRegister(oper.num);
        return nasmRegister;
    }

    @Override
    public NasmOperand visit(C3aVar oper) {
        System.out.println("C3aVar");
        // Si variable globale
        if (table.getVar(oper.item.identif) != null) {
            NasmLabel nasmLabel = new NasmLabel(oper.item.identif);
            return new NasmAddress(nasmLabel, '-', null);
        }
        NasmRegister nasmRegister = new NasmRegister(Nasm.REG_EBP);
        nasmRegister.colorRegister(Nasm.REG_EBP);
        int base = oper.item.adresse + oper.item.taille;
        return new NasmAddress(nasmRegister, '-', new NasmConstant(base));
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        System.out.println("C3aFunction");
        return new NasmLabel(oper.val.identif);
    }
}
