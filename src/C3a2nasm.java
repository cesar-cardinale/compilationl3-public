import c3a.*;
import nasm.*;
import ts.Ts;
import ts.TsItemVar;

public class C3a2nasm implements C3aVisitor <NasmOperand> {

    private Nasm nasm;
    private C3a c3a;
    private Ts table;

    public C3a2nasm(C3a c3a, Ts ts) {
        this.nasm = new Nasm(ts);
        this.c3a = c3a;
        this.table = ts;
        this.c3a.listeInst.get(0).accept(this);
    }

    public Nasm getNasm() {
        return nasm;
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
        NasmRegister nasmRegister = new NasmRegister(0);
        nasmRegister.colorRegister(-1);

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
        NasmConstant nasmConstant1 = new NasmConstant(inst.op1.val.nbArgs*4);
        NasmAdd nasmAdd = new NasmAdd(null, nasmRegister, nasmConstant1, "désallocation des arguments");
        nasm.ajouteInst(nasmAdd);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmRegister nasmRegisterEBP = nasm.newRegister();
        nasmRegisterEBP.colorRegister(-2); // EBP

        NasmRegister nasmRegisterESP = nasm.newRegister();
        nasmRegisterESP.colorRegister(-1); // ESP

        NasmPush nasmPush = new NasmPush(label, nasmRegisterEBP,"sauvegarde la valeur de ebp");
        nasm.ajouteInst(nasmPush);

        NasmMov nasmMov = new NasmMov(null, nasmRegisterEBP,nasmRegisterESP, "nouvelle valeur de ebp");
        nasm.ajouteInst(nasmMov);

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

        NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "on passe par un registre temporaire");
        nasm.ajouteInst(nasmCmp);

        NasmJl nasmJl = new NasmJl(null, result, "Jump");
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
        //Pas sur

        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);

        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmMov(label, dest, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "on passe par un registre temporaire");
        nasm.ajouteInst(nasmCmp);

        NasmJe nasmJe = new NasmJe(null, result, "Jump");
        nasm.ajouteInst(nasmJe);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "on passe par un registre temporaire");
        nasm.ajouteInst(nasmCmp);

        NasmJne nasmJne = new NasmJne(null, result, "Jump");
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
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmRegister nasmRegister = nasm.newRegister();
        NasmOperand nasmOperand = inst.op1.accept(this);

        NasmMov nasmMov = new NasmMov(null, nasmRegister, nasmOperand, "Write 1");
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
        return null;
    }

    @Override
    public NasmOperand visit(C3aTemp oper) {
        return new NasmRegister(oper.num);
    }

    @Override
    public NasmOperand visit(C3aVar oper) {
        return oper.index.accept(this);
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel(oper.val.identif);
    }
}
