/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstructionVide extends PInstructionVide
{
    private TPointVirgule _pointVirgule_;

    public AInstructionVide()
    {
        // Constructor
    }

    public AInstructionVide(
        @SuppressWarnings("hiding") TPointVirgule _pointVirgule_)
    {
        // Constructor
        setPointVirgule(_pointVirgule_);

    }

    @Override
    public Object clone()
    {
        return new AInstructionVide(
            cloneNode(this._pointVirgule_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstructionVide(this);
    }

    public TPointVirgule getPointVirgule()
    {
        return this._pointVirgule_;
    }

    public void setPointVirgule(TPointVirgule node)
    {
        if(this._pointVirgule_ != null)
        {
            this._pointVirgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pointVirgule_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._pointVirgule_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._pointVirgule_ == child)
        {
            this._pointVirgule_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._pointVirgule_ == oldChild)
        {
            setPointVirgule((TPointVirgule) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}