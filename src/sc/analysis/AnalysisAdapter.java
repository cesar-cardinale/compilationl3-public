/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import sc.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAProgrammeProgramme(AProgrammeProgramme node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListeVarVar(AListeVarVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarVar(AVarVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOuExpression(AOuExpression node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp2Expression(AExp2Expression node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEtExp2(AEtExp2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEgalExp3(AEgalExp3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInferieurExp3(AInferieurExp3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPlusExp4(APlusExp4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMoinsExp4(AMoinsExp4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFoisExp5(AFoisExp5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADiviserExp5(ADiviserExp5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANonExp6(ANonExp6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp7Exp6(AExp7Exp6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANombreExp7(ANombreExp7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarExp7(AVarExp7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParanthesesExp7(AParanthesesExp7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAppelfonctionExp7(AAppelfonctionExp7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALireExp7(ALireExp7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARecursifListeExp(ARecursifListeExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFinalListeExp(AFinalListeExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFinalListeExpBis(AFinalListeExpBis node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARecursifListeExpBis(ARecursifListeExpBis node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionAffectationInstruction(AInstructionAffectationInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionBlocInstruction(AInstructionBlocInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionSiInstruction(AInstructionSiInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionTantqueInstruction(AInstructionTantqueInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionRappelInstruction(AInstructionRappelInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionRetourInstruction(AInstructionRetourInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionEcrireInstruction(AInstructionEcrireInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionVideInstruction(AInstructionVideInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListeInstructionListeInstruction(AListeInstructionListeInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListeFinaleListeInstruction(AListeFinaleListeInstruction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionAffectation(AInstructionAffectation node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionBlocInstructionBloc(AInstructionBlocInstructionBloc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAvecsinonInstructionSi(AAvecsinonInstructionSi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASanssinonInstructionSi(ASanssinonInstructionSi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionSinon(AInstructionSinon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionTantque(AInstructionTantque node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionRappel(AInstructionRappel node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionRetour(AInstructionRetour node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionEcrire(AInstructionEcrire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstructionVide(AInstructionVide node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADecvarDecvar(ADecvarDecvar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvarDecvar(AListedecvarDecvar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvarListedecvar(AListedecvarListedecvar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvarFinListedecvar(AListedecvarFinListedecvar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvarbisListedecvarbis(AListedecvarbisListedecvarbis node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvarbisFinListedecvarbis(AListedecvarbisFinListedecvarbis node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvaroptListedecvaropt(AListedecvaroptListedecvaropt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAvecparamAppelfonction(AAvecparamAppelfonction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASansparamAppelfonction(ASansparamAppelfonction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADecfoncDecfonc(ADecfoncDecfonc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecfoncListedecfonc(AListedecfoncListedecfonc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListedecfoncFinListedecfonc(AListedecfoncFinListedecfonc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEspaces(TEspaces node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCommentaire(TCommentaire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMoins(TMoins node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFois(TFois node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiviser(TDiviser node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPointVirgule(TPointVirgule node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVirgule(TVirgule node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTParantheseOuvrante(TParantheseOuvrante node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTParantheseFermante(TParantheseFermante node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAccoladeOuvrante(TAccoladeOuvrante node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAccoladeFermante(TAccoladeFermante node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCrochetOuvrant(TCrochetOuvrant node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCrochetFermant(TCrochetFermant node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTInferieur(TInferieur node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSuperieur(TSuperieur node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEgal(TEgal node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNon(TNon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEt(TEt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTOu(TOu node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNombre(TNombre node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSi(TSi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSinon(TSinon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTantque(TTantque node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAlors(TAlors node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFaire(TFaire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRetour(TRetour node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEntier(TEntier node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLire(TLire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEcrire(TEcrire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTIdentifiant(TIdentifiant node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}