/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstructionBlocInstructionBloc extends PInstructionBloc
{
    private TAccoladeOuvrante _accoladeOuvrante_;
    private PListeInstruction _listeInstruction_;
    private TAccoladeFermante _accoladeFermante_;

    public AInstructionBlocInstructionBloc()
    {
        // Constructor
    }

    public AInstructionBlocInstructionBloc(
        @SuppressWarnings("hiding") TAccoladeOuvrante _accoladeOuvrante_,
        @SuppressWarnings("hiding") PListeInstruction _listeInstruction_,
        @SuppressWarnings("hiding") TAccoladeFermante _accoladeFermante_)
    {
        // Constructor
        setAccoladeOuvrante(_accoladeOuvrante_);

        setListeInstruction(_listeInstruction_);

        setAccoladeFermante(_accoladeFermante_);

    }

    @Override
    public Object clone()
    {
        return new AInstructionBlocInstructionBloc(
            cloneNode(this._accoladeOuvrante_),
            cloneNode(this._listeInstruction_),
            cloneNode(this._accoladeFermante_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstructionBlocInstructionBloc(this);
    }

    public TAccoladeOuvrante getAccoladeOuvrante()
    {
        return this._accoladeOuvrante_;
    }

    public void setAccoladeOuvrante(TAccoladeOuvrante node)
    {
        if(this._accoladeOuvrante_ != null)
        {
            this._accoladeOuvrante_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._accoladeOuvrante_ = node;
    }

    public PListeInstruction getListeInstruction()
    {
        return this._listeInstruction_;
    }

    public void setListeInstruction(PListeInstruction node)
    {
        if(this._listeInstruction_ != null)
        {
            this._listeInstruction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listeInstruction_ = node;
    }

    public TAccoladeFermante getAccoladeFermante()
    {
        return this._accoladeFermante_;
    }

    public void setAccoladeFermante(TAccoladeFermante node)
    {
        if(this._accoladeFermante_ != null)
        {
            this._accoladeFermante_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._accoladeFermante_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._accoladeOuvrante_)
            + toString(this._listeInstruction_)
            + toString(this._accoladeFermante_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._accoladeOuvrante_ == child)
        {
            this._accoladeOuvrante_ = null;
            return;
        }

        if(this._listeInstruction_ == child)
        {
            this._listeInstruction_ = null;
            return;
        }

        if(this._accoladeFermante_ == child)
        {
            this._accoladeFermante_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._accoladeOuvrante_ == oldChild)
        {
            setAccoladeOuvrante((TAccoladeOuvrante) newChild);
            return;
        }

        if(this._listeInstruction_ == oldChild)
        {
            setListeInstruction((PListeInstruction) newChild);
            return;
        }

        if(this._accoladeFermante_ == oldChild)
        {
            setAccoladeFermante((TAccoladeFermante) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
