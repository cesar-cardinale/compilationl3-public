/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ARecursifListeExp extends PListeExp
{
    private PExpression _expression_;
    private PListeExpBis _listeExpBis_;

    public ARecursifListeExp()
    {
        // Constructor
    }

    public ARecursifListeExp(
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") PListeExpBis _listeExpBis_)
    {
        // Constructor
        setExpression(_expression_);

        setListeExpBis(_listeExpBis_);

    }

    @Override
    public Object clone()
    {
        return new ARecursifListeExp(
            cloneNode(this._expression_),
            cloneNode(this._listeExpBis_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARecursifListeExp(this);
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

    public PListeExpBis getListeExpBis()
    {
        return this._listeExpBis_;
    }

    public void setListeExpBis(PListeExpBis node)
    {
        if(this._listeExpBis_ != null)
        {
            this._listeExpBis_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listeExpBis_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expression_)
            + toString(this._listeExpBis_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._listeExpBis_ == child)
        {
            this._listeExpBis_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(this._listeExpBis_ == oldChild)
        {
            setListeExpBis((PListeExpBis) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
