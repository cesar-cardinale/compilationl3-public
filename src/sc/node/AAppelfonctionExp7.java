/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AAppelfonctionExp7 extends PExp7
{
    private PAppelfonction _appelfonction_;

    public AAppelfonctionExp7()
    {
        // Constructor
    }

    public AAppelfonctionExp7(
        @SuppressWarnings("hiding") PAppelfonction _appelfonction_)
    {
        // Constructor
        setAppelfonction(_appelfonction_);

    }

    @Override
    public Object clone()
    {
        return new AAppelfonctionExp7(
            cloneNode(this._appelfonction_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAppelfonctionExp7(this);
    }

    public PAppelfonction getAppelfonction()
    {
        return this._appelfonction_;
    }

    public void setAppelfonction(PAppelfonction node)
    {
        if(this._appelfonction_ != null)
        {
            this._appelfonction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._appelfonction_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._appelfonction_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._appelfonction_ == child)
        {
            this._appelfonction_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._appelfonction_ == oldChild)
        {
            setAppelfonction((PAppelfonction) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
