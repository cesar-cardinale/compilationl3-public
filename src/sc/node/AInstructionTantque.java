/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstructionTantque extends PInstructionTantque
{
    private TTantque _tantque_;
    private PExpression _expression_;
    private TFaire _faire_;
    private PInstructionBloc _instructionBloc_;

    public AInstructionTantque()
    {
        // Constructor
    }

    public AInstructionTantque(
        @SuppressWarnings("hiding") TTantque _tantque_,
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") TFaire _faire_,
        @SuppressWarnings("hiding") PInstructionBloc _instructionBloc_)
    {
        // Constructor
        setTantque(_tantque_);

        setExpression(_expression_);

        setFaire(_faire_);

        setInstructionBloc(_instructionBloc_);

    }

    @Override
    public Object clone()
    {
        return new AInstructionTantque(
            cloneNode(this._tantque_),
            cloneNode(this._expression_),
            cloneNode(this._faire_),
            cloneNode(this._instructionBloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstructionTantque(this);
    }

    public TTantque getTantque()
    {
        return this._tantque_;
    }

    public void setTantque(TTantque node)
    {
        if(this._tantque_ != null)
        {
            this._tantque_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._tantque_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    public TFaire getFaire()
    {
        return this._faire_;
    }

    public void setFaire(TFaire node)
    {
        if(this._faire_ != null)
        {
            this._faire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._faire_ = node;
    }

    public PInstructionBloc getInstructionBloc()
    {
        return this._instructionBloc_;
    }

    public void setInstructionBloc(PInstructionBloc node)
    {
        if(this._instructionBloc_ != null)
        {
            this._instructionBloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instructionBloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._tantque_)
            + toString(this._expression_)
            + toString(this._faire_)
            + toString(this._instructionBloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._tantque_ == child)
        {
            this._tantque_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._faire_ == child)
        {
            this._faire_ = null;
            return;
        }

        if(this._instructionBloc_ == child)
        {
            this._instructionBloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._tantque_ == oldChild)
        {
            setTantque((TTantque) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(this._faire_ == oldChild)
        {
            setFaire((TFaire) newChild);
            return;
        }

        if(this._instructionBloc_ == oldChild)
        {
            setInstructionBloc((PInstructionBloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
