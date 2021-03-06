/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgramme().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProgrammeProgramme(AProgrammeProgramme node)
    {
        defaultIn(node);
    }

    public void outAProgrammeProgramme(AProgrammeProgramme node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgrammeProgramme(AProgrammeProgramme node)
    {
        inAProgrammeProgramme(node);
        if(node.getListedecvaropt() != null)
        {
            node.getListedecvaropt().apply(this);
        }
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
        }
        outAProgrammeProgramme(node);
    }

    public void inAListeVarVar(AListeVarVar node)
    {
        defaultIn(node);
    }

    public void outAListeVarVar(AListeVarVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeVarVar(AListeVarVar node)
    {
        inAListeVarVar(node);
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        if(node.getCrochetOuvrant() != null)
        {
            node.getCrochetOuvrant().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getCrochetFermant() != null)
        {
            node.getCrochetFermant().apply(this);
        }
        outAListeVarVar(node);
    }

    public void inAVarVar(AVarVar node)
    {
        defaultIn(node);
    }

    public void outAVarVar(AVarVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarVar(AVarVar node)
    {
        inAVarVar(node);
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        outAVarVar(node);
    }

    public void inAOuExpression(AOuExpression node)
    {
        defaultIn(node);
    }

    public void outAOuExpression(AOuExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOuExpression(AOuExpression node)
    {
        inAOuExpression(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        outAOuExpression(node);
    }

    public void inAExp2Expression(AExp2Expression node)
    {
        defaultIn(node);
    }

    public void outAExp2Expression(AExp2Expression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp2Expression(AExp2Expression node)
    {
        inAExp2Expression(node);
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        outAExp2Expression(node);
    }

    public void inAEtExp2(AEtExp2 node)
    {
        defaultIn(node);
    }

    public void outAEtExp2(AEtExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEtExp2(AEtExp2 node)
    {
        inAEtExp2(node);
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        outAEtExp2(node);
    }

    public void inAExp3Exp2(AExp3Exp2 node)
    {
        defaultIn(node);
    }

    public void outAExp3Exp2(AExp3Exp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node)
    {
        inAExp3Exp2(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        outAExp3Exp2(node);
    }

    public void inAEgalExp3(AEgalExp3 node)
    {
        defaultIn(node);
    }

    public void outAEgalExp3(AEgalExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEgalExp3(AEgalExp3 node)
    {
        inAEgalExp3(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outAEgalExp3(node);
    }

    public void inAInferieurExp3(AInferieurExp3 node)
    {
        defaultIn(node);
    }

    public void outAInferieurExp3(AInferieurExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInferieurExp3(AInferieurExp3 node)
    {
        inAInferieurExp3(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        if(node.getInferieur() != null)
        {
            node.getInferieur().apply(this);
        }
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outAInferieurExp3(node);
    }

    public void inAExp4Exp3(AExp4Exp3 node)
    {
        defaultIn(node);
    }

    public void outAExp4Exp3(AExp4Exp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node)
    {
        inAExp4Exp3(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outAExp4Exp3(node);
    }

    public void inAPlusExp4(APlusExp4 node)
    {
        defaultIn(node);
    }

    public void outAPlusExp4(APlusExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExp4(APlusExp4 node)
    {
        inAPlusExp4(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        outAPlusExp4(node);
    }

    public void inAMoinsExp4(AMoinsExp4 node)
    {
        defaultIn(node);
    }

    public void outAMoinsExp4(AMoinsExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoinsExp4(AMoinsExp4 node)
    {
        inAMoinsExp4(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        if(node.getMoins() != null)
        {
            node.getMoins().apply(this);
        }
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        outAMoinsExp4(node);
    }

    public void inAExp5Exp4(AExp5Exp4 node)
    {
        defaultIn(node);
    }

    public void outAExp5Exp4(AExp5Exp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node)
    {
        inAExp5Exp4(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        outAExp5Exp4(node);
    }

    public void inAFoisExp5(AFoisExp5 node)
    {
        defaultIn(node);
    }

    public void outAFoisExp5(AFoisExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFoisExp5(AFoisExp5 node)
    {
        inAFoisExp5(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        if(node.getFois() != null)
        {
            node.getFois().apply(this);
        }
        if(node.getExp6() != null)
        {
            node.getExp6().apply(this);
        }
        outAFoisExp5(node);
    }

    public void inADiviserExp5(ADiviserExp5 node)
    {
        defaultIn(node);
    }

    public void outADiviserExp5(ADiviserExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADiviserExp5(ADiviserExp5 node)
    {
        inADiviserExp5(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        if(node.getDiviser() != null)
        {
            node.getDiviser().apply(this);
        }
        if(node.getExp6() != null)
        {
            node.getExp6().apply(this);
        }
        outADiviserExp5(node);
    }

    public void inAExp6Exp5(AExp6Exp5 node)
    {
        defaultIn(node);
    }

    public void outAExp6Exp5(AExp6Exp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node)
    {
        inAExp6Exp5(node);
        if(node.getExp6() != null)
        {
            node.getExp6().apply(this);
        }
        outAExp6Exp5(node);
    }

    public void inANonExp6(ANonExp6 node)
    {
        defaultIn(node);
    }

    public void outANonExp6(ANonExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANonExp6(ANonExp6 node)
    {
        inANonExp6(node);
        if(node.getNon() != null)
        {
            node.getNon().apply(this);
        }
        if(node.getExp6() != null)
        {
            node.getExp6().apply(this);
        }
        outANonExp6(node);
    }

    public void inAExp7Exp6(AExp7Exp6 node)
    {
        defaultIn(node);
    }

    public void outAExp7Exp6(AExp7Exp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp7Exp6(AExp7Exp6 node)
    {
        inAExp7Exp6(node);
        if(node.getExp7() != null)
        {
            node.getExp7().apply(this);
        }
        outAExp7Exp6(node);
    }

    public void inANombreExp7(ANombreExp7 node)
    {
        defaultIn(node);
    }

    public void outANombreExp7(ANombreExp7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANombreExp7(ANombreExp7 node)
    {
        inANombreExp7(node);
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        outANombreExp7(node);
    }

    public void inAVarExp7(AVarExp7 node)
    {
        defaultIn(node);
    }

    public void outAVarExp7(AVarExp7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarExp7(AVarExp7 node)
    {
        inAVarExp7(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVarExp7(node);
    }

    public void inAParanthesesExp7(AParanthesesExp7 node)
    {
        defaultIn(node);
    }

    public void outAParanthesesExp7(AParanthesesExp7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParanthesesExp7(AParanthesesExp7 node)
    {
        inAParanthesesExp7(node);
        if(node.getParantheseOuvrante() != null)
        {
            node.getParantheseOuvrante().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getParantheseFermante() != null)
        {
            node.getParantheseFermante().apply(this);
        }
        outAParanthesesExp7(node);
    }

    public void inAAppelfonctionExp7(AAppelfonctionExp7 node)
    {
        defaultIn(node);
    }

    public void outAAppelfonctionExp7(AAppelfonctionExp7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelfonctionExp7(AAppelfonctionExp7 node)
    {
        inAAppelfonctionExp7(node);
        if(node.getAppelfonction() != null)
        {
            node.getAppelfonction().apply(this);
        }
        outAAppelfonctionExp7(node);
    }

    public void inALireExp7(ALireExp7 node)
    {
        defaultIn(node);
    }

    public void outALireExp7(ALireExp7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireExp7(ALireExp7 node)
    {
        inALireExp7(node);
        if(node.getLire() != null)
        {
            node.getLire().apply(this);
        }
        if(node.getParantheseOuvrante() != null)
        {
            node.getParantheseOuvrante().apply(this);
        }
        if(node.getParantheseFermante() != null)
        {
            node.getParantheseFermante().apply(this);
        }
        outALireExp7(node);
    }

    public void inARecursifListeExp(ARecursifListeExp node)
    {
        defaultIn(node);
    }

    public void outARecursifListeExp(ARecursifListeExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARecursifListeExp(ARecursifListeExp node)
    {
        inARecursifListeExp(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getListeExpBis() != null)
        {
            node.getListeExpBis().apply(this);
        }
        outARecursifListeExp(node);
    }

    public void inAFinalListeExp(AFinalListeExp node)
    {
        defaultIn(node);
    }

    public void outAFinalListeExp(AFinalListeExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFinalListeExp(AFinalListeExp node)
    {
        inAFinalListeExp(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAFinalListeExp(node);
    }

    public void inAFinalListeExpBis(AFinalListeExpBis node)
    {
        defaultIn(node);
    }

    public void outAFinalListeExpBis(AFinalListeExpBis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFinalListeExpBis(AFinalListeExpBis node)
    {
        inAFinalListeExpBis(node);
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAFinalListeExpBis(node);
    }

    public void inARecursifListeExpBis(ARecursifListeExpBis node)
    {
        defaultIn(node);
    }

    public void outARecursifListeExpBis(ARecursifListeExpBis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARecursifListeExpBis(ARecursifListeExpBis node)
    {
        inARecursifListeExpBis(node);
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getListeExpBis() != null)
        {
            node.getListeExpBis().apply(this);
        }
        outARecursifListeExpBis(node);
    }

    public void inAInstructionAffectationInstruction(AInstructionAffectationInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionAffectationInstruction(AInstructionAffectationInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionAffectationInstruction(AInstructionAffectationInstruction node)
    {
        inAInstructionAffectationInstruction(node);
        if(node.getInstructionAffectation() != null)
        {
            node.getInstructionAffectation().apply(this);
        }
        outAInstructionAffectationInstruction(node);
    }

    public void inAInstructionBlocInstruction(AInstructionBlocInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionBlocInstruction(AInstructionBlocInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionBlocInstruction(AInstructionBlocInstruction node)
    {
        inAInstructionBlocInstruction(node);
        if(node.getInstructionBloc() != null)
        {
            node.getInstructionBloc().apply(this);
        }
        outAInstructionBlocInstruction(node);
    }

    public void inAInstructionSiInstruction(AInstructionSiInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionSiInstruction(AInstructionSiInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionSiInstruction(AInstructionSiInstruction node)
    {
        inAInstructionSiInstruction(node);
        if(node.getInstructionSi() != null)
        {
            node.getInstructionSi().apply(this);
        }
        outAInstructionSiInstruction(node);
    }

    public void inAInstructionTantqueInstruction(AInstructionTantqueInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionTantqueInstruction(AInstructionTantqueInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionTantqueInstruction(AInstructionTantqueInstruction node)
    {
        inAInstructionTantqueInstruction(node);
        if(node.getInstructionTantque() != null)
        {
            node.getInstructionTantque().apply(this);
        }
        outAInstructionTantqueInstruction(node);
    }

    public void inAInstructionRappelInstruction(AInstructionRappelInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionRappelInstruction(AInstructionRappelInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionRappelInstruction(AInstructionRappelInstruction node)
    {
        inAInstructionRappelInstruction(node);
        if(node.getInstructionRappel() != null)
        {
            node.getInstructionRappel().apply(this);
        }
        outAInstructionRappelInstruction(node);
    }

    public void inAInstructionRetourInstruction(AInstructionRetourInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionRetourInstruction(AInstructionRetourInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionRetourInstruction(AInstructionRetourInstruction node)
    {
        inAInstructionRetourInstruction(node);
        if(node.getInstructionRetour() != null)
        {
            node.getInstructionRetour().apply(this);
        }
        outAInstructionRetourInstruction(node);
    }

    public void inAInstructionEcrireInstruction(AInstructionEcrireInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionEcrireInstruction(AInstructionEcrireInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionEcrireInstruction(AInstructionEcrireInstruction node)
    {
        inAInstructionEcrireInstruction(node);
        if(node.getInstructionEcrire() != null)
        {
            node.getInstructionEcrire().apply(this);
        }
        outAInstructionEcrireInstruction(node);
    }

    public void inAInstructionVideInstruction(AInstructionVideInstruction node)
    {
        defaultIn(node);
    }

    public void outAInstructionVideInstruction(AInstructionVideInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionVideInstruction(AInstructionVideInstruction node)
    {
        inAInstructionVideInstruction(node);
        if(node.getInstructionVide() != null)
        {
            node.getInstructionVide().apply(this);
        }
        outAInstructionVideInstruction(node);
    }

    public void inAListeInstructionListeInstruction(AListeInstructionListeInstruction node)
    {
        defaultIn(node);
    }

    public void outAListeInstructionListeInstruction(AListeInstructionListeInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeInstructionListeInstruction(AListeInstructionListeInstruction node)
    {
        inAListeInstructionListeInstruction(node);
        if(node.getInstruction() != null)
        {
            node.getInstruction().apply(this);
        }
        if(node.getListeInstruction() != null)
        {
            node.getListeInstruction().apply(this);
        }
        outAListeInstructionListeInstruction(node);
    }

    public void inAListeFinaleListeInstruction(AListeFinaleListeInstruction node)
    {
        defaultIn(node);
    }

    public void outAListeFinaleListeInstruction(AListeFinaleListeInstruction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeFinaleListeInstruction(AListeFinaleListeInstruction node)
    {
        inAListeFinaleListeInstruction(node);
        outAListeFinaleListeInstruction(node);
    }

    public void inAInstructionAffectation(AInstructionAffectation node)
    {
        defaultIn(node);
    }

    public void outAInstructionAffectation(AInstructionAffectation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionAffectation(AInstructionAffectation node)
    {
        inAInstructionAffectation(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        outAInstructionAffectation(node);
    }

    public void inAInstructionBlocInstructionBloc(AInstructionBlocInstructionBloc node)
    {
        defaultIn(node);
    }

    public void outAInstructionBlocInstructionBloc(AInstructionBlocInstructionBloc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionBlocInstructionBloc(AInstructionBlocInstructionBloc node)
    {
        inAInstructionBlocInstructionBloc(node);
        if(node.getAccoladeOuvrante() != null)
        {
            node.getAccoladeOuvrante().apply(this);
        }
        if(node.getListeInstruction() != null)
        {
            node.getListeInstruction().apply(this);
        }
        if(node.getAccoladeFermante() != null)
        {
            node.getAccoladeFermante().apply(this);
        }
        outAInstructionBlocInstructionBloc(node);
    }

    public void inAAvecsinonInstructionSi(AAvecsinonInstructionSi node)
    {
        defaultIn(node);
    }

    public void outAAvecsinonInstructionSi(AAvecsinonInstructionSi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAvecsinonInstructionSi(AAvecsinonInstructionSi node)
    {
        inAAvecsinonInstructionSi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getInstructionBloc() != null)
        {
            node.getInstructionBloc().apply(this);
        }
        if(node.getInstructionSinon() != null)
        {
            node.getInstructionSinon().apply(this);
        }
        outAAvecsinonInstructionSi(node);
    }

    public void inASanssinonInstructionSi(ASanssinonInstructionSi node)
    {
        defaultIn(node);
    }

    public void outASanssinonInstructionSi(ASanssinonInstructionSi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASanssinonInstructionSi(ASanssinonInstructionSi node)
    {
        inASanssinonInstructionSi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getInstructionBloc() != null)
        {
            node.getInstructionBloc().apply(this);
        }
        outASanssinonInstructionSi(node);
    }

    public void inAInstructionSinon(AInstructionSinon node)
    {
        defaultIn(node);
    }

    public void outAInstructionSinon(AInstructionSinon node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionSinon(AInstructionSinon node)
    {
        inAInstructionSinon(node);
        if(node.getSinon() != null)
        {
            node.getSinon().apply(this);
        }
        if(node.getInstructionBloc() != null)
        {
            node.getInstructionBloc().apply(this);
        }
        outAInstructionSinon(node);
    }

    public void inAInstructionTantque(AInstructionTantque node)
    {
        defaultIn(node);
    }

    public void outAInstructionTantque(AInstructionTantque node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionTantque(AInstructionTantque node)
    {
        inAInstructionTantque(node);
        if(node.getTantque() != null)
        {
            node.getTantque().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getFaire() != null)
        {
            node.getFaire().apply(this);
        }
        if(node.getInstructionBloc() != null)
        {
            node.getInstructionBloc().apply(this);
        }
        outAInstructionTantque(node);
    }

    public void inAInstructionRappel(AInstructionRappel node)
    {
        defaultIn(node);
    }

    public void outAInstructionRappel(AInstructionRappel node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionRappel(AInstructionRappel node)
    {
        inAInstructionRappel(node);
        if(node.getAppelfonction() != null)
        {
            node.getAppelfonction().apply(this);
        }
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        outAInstructionRappel(node);
    }

    public void inAInstructionRetour(AInstructionRetour node)
    {
        defaultIn(node);
    }

    public void outAInstructionRetour(AInstructionRetour node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionRetour(AInstructionRetour node)
    {
        inAInstructionRetour(node);
        if(node.getRetour() != null)
        {
            node.getRetour().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        outAInstructionRetour(node);
    }

    public void inAInstructionEcrire(AInstructionEcrire node)
    {
        defaultIn(node);
    }

    public void outAInstructionEcrire(AInstructionEcrire node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionEcrire(AInstructionEcrire node)
    {
        inAInstructionEcrire(node);
        if(node.getEcrire() != null)
        {
            node.getEcrire().apply(this);
        }
        if(node.getParantheseOuvrante() != null)
        {
            node.getParantheseOuvrante().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getParantheseFermante() != null)
        {
            node.getParantheseFermante().apply(this);
        }
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        outAInstructionEcrire(node);
    }

    public void inAInstructionVide(AInstructionVide node)
    {
        defaultIn(node);
    }

    public void outAInstructionVide(AInstructionVide node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstructionVide(AInstructionVide node)
    {
        inAInstructionVide(node);
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        outAInstructionVide(node);
    }

    public void inADecvarDecvar(ADecvarDecvar node)
    {
        defaultIn(node);
    }

    public void outADecvarDecvar(ADecvarDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvarDecvar(ADecvarDecvar node)
    {
        inADecvarDecvar(node);
        if(node.getEntier() != null)
        {
            node.getEntier().apply(this);
        }
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        outADecvarDecvar(node);
    }

    public void inAListedecvarDecvar(AListedecvarDecvar node)
    {
        defaultIn(node);
    }

    public void outAListedecvarDecvar(AListedecvarDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvarDecvar(AListedecvarDecvar node)
    {
        inAListedecvarDecvar(node);
        if(node.getEntier() != null)
        {
            node.getEntier().apply(this);
        }
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        if(node.getCrochetOuvrant() != null)
        {
            node.getCrochetOuvrant().apply(this);
        }
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        if(node.getCrochetFermant() != null)
        {
            node.getCrochetFermant().apply(this);
        }
        outAListedecvarDecvar(node);
    }

    public void inAListedecvarListedecvar(AListedecvarListedecvar node)
    {
        defaultIn(node);
    }

    public void outAListedecvarListedecvar(AListedecvarListedecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvarListedecvar(AListedecvarListedecvar node)
    {
        inAListedecvarListedecvar(node);
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        if(node.getListedecvarbis() != null)
        {
            node.getListedecvarbis().apply(this);
        }
        outAListedecvarListedecvar(node);
    }

    public void inAListedecvarFinListedecvar(AListedecvarFinListedecvar node)
    {
        defaultIn(node);
    }

    public void outAListedecvarFinListedecvar(AListedecvarFinListedecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvarFinListedecvar(AListedecvarFinListedecvar node)
    {
        inAListedecvarFinListedecvar(node);
        outAListedecvarFinListedecvar(node);
    }

    public void inAListedecvarbisListedecvarbis(AListedecvarbisListedecvarbis node)
    {
        defaultIn(node);
    }

    public void outAListedecvarbisListedecvarbis(AListedecvarbisListedecvarbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvarbisListedecvarbis(AListedecvarbisListedecvarbis node)
    {
        inAListedecvarbisListedecvarbis(node);
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        if(node.getListedecvarbis() != null)
        {
            node.getListedecvarbis().apply(this);
        }
        outAListedecvarbisListedecvarbis(node);
    }

    public void inAListedecvarbisFinListedecvarbis(AListedecvarbisFinListedecvarbis node)
    {
        defaultIn(node);
    }

    public void outAListedecvarbisFinListedecvarbis(AListedecvarbisFinListedecvarbis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvarbisFinListedecvarbis(AListedecvarbisFinListedecvarbis node)
    {
        inAListedecvarbisFinListedecvarbis(node);
        outAListedecvarbisFinListedecvarbis(node);
    }

    public void inAListedecvaroptListedecvaropt(AListedecvaroptListedecvaropt node)
    {
        defaultIn(node);
    }

    public void outAListedecvaroptListedecvaropt(AListedecvaroptListedecvaropt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvaroptListedecvaropt(AListedecvaroptListedecvaropt node)
    {
        inAListedecvaroptListedecvaropt(node);
        if(node.getListedecvar() != null)
        {
            node.getListedecvar().apply(this);
        }
        if(node.getPointVirgule() != null)
        {
            node.getPointVirgule().apply(this);
        }
        outAListedecvaroptListedecvaropt(node);
    }

    public void inAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node)
    {
        defaultIn(node);
    }

    public void outAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecvaroptFinListedecvaropt(AListedecvaroptFinListedecvaropt node)
    {
        inAListedecvaroptFinListedecvaropt(node);
        outAListedecvaroptFinListedecvaropt(node);
    }

    public void inAAvecparamAppelfonction(AAvecparamAppelfonction node)
    {
        defaultIn(node);
    }

    public void outAAvecparamAppelfonction(AAvecparamAppelfonction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAvecparamAppelfonction(AAvecparamAppelfonction node)
    {
        inAAvecparamAppelfonction(node);
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        if(node.getParantheseOuvrante() != null)
        {
            node.getParantheseOuvrante().apply(this);
        }
        if(node.getListeExp() != null)
        {
            node.getListeExp().apply(this);
        }
        if(node.getParantheseFermante() != null)
        {
            node.getParantheseFermante().apply(this);
        }
        outAAvecparamAppelfonction(node);
    }

    public void inASansparamAppelfonction(ASansparamAppelfonction node)
    {
        defaultIn(node);
    }

    public void outASansparamAppelfonction(ASansparamAppelfonction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASansparamAppelfonction(ASansparamAppelfonction node)
    {
        inASansparamAppelfonction(node);
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        if(node.getParantheseOuvrante() != null)
        {
            node.getParantheseOuvrante().apply(this);
        }
        if(node.getParantheseFermante() != null)
        {
            node.getParantheseFermante().apply(this);
        }
        outASansparamAppelfonction(node);
    }

    public void inADecfoncDecfonc(ADecfoncDecfonc node)
    {
        defaultIn(node);
    }

    public void outADecfoncDecfonc(ADecfoncDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecfoncDecfonc(ADecfoncDecfonc node)
    {
        inADecfoncDecfonc(node);
        if(node.getIdentifiant() != null)
        {
            node.getIdentifiant().apply(this);
        }
        if(node.getParantheseOuvrante() != null)
        {
            node.getParantheseOuvrante().apply(this);
        }
        if(node.getListedecvar() != null)
        {
            node.getListedecvar().apply(this);
        }
        if(node.getParantheseFermante() != null)
        {
            node.getParantheseFermante().apply(this);
        }
        if(node.getListedecvaropt() != null)
        {
            node.getListedecvaropt().apply(this);
        }
        if(node.getInstructionBloc() != null)
        {
            node.getInstructionBloc().apply(this);
        }
        outADecfoncDecfonc(node);
    }

    public void inAListedecfoncListedecfonc(AListedecfoncListedecfonc node)
    {
        defaultIn(node);
    }

    public void outAListedecfoncListedecfonc(AListedecfoncListedecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecfoncListedecfonc(AListedecfoncListedecfonc node)
    {
        inAListedecfoncListedecfonc(node);
        if(node.getDecfonc() != null)
        {
            node.getDecfonc().apply(this);
        }
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
        }
        outAListedecfoncListedecfonc(node);
    }

    public void inAListedecfoncFinListedecfonc(AListedecfoncFinListedecfonc node)
    {
        defaultIn(node);
    }

    public void outAListedecfoncFinListedecfonc(AListedecfoncFinListedecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListedecfoncFinListedecfonc(AListedecfoncFinListedecfonc node)
    {
        inAListedecfoncFinListedecfonc(node);
        outAListedecfoncFinListedecfonc(node);
    }
}
