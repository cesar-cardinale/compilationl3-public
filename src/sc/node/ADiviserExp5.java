/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ADiviserExp5 extends PExp5
{
    private PExp5 _exp5_;
    private TDiviser _diviser_;
    private PExp6 _exp6_;

    public ADiviserExp5()
    {
        // Constructor
    }

    public ADiviserExp5(
        @SuppressWarnings("hiding") PExp5 _exp5_,
        @SuppressWarnings("hiding") TDiviser _diviser_,
        @SuppressWarnings("hiding") PExp6 _exp6_)
    {
        // Constructor
        setExp5(_exp5_);

        setDiviser(_diviser_);

        setExp6(_exp6_);

    }

    @Override
    public Object clone()
    {
        return new ADiviserExp5(
            cloneNode(this._exp5_),
            cloneNode(this._diviser_),
            cloneNode(this._exp6_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADiviserExp5(this);
    }

    public PExp5 getExp5()
    {
        return this._exp5_;
    }

    public void setExp5(PExp5 node)
    {
        if(this._exp5_ != null)
        {
            this._exp5_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp5_ = node;
    }

    public TDiviser getDiviser()
    {
        return this._diviser_;
    }

    public void setDiviser(TDiviser node)
    {
        if(this._diviser_ != null)
        {
            this._diviser_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._diviser_ = node;
    }

    public PExp6 getExp6()
    {
        return this._exp6_;
    }

    public void setExp6(PExp6 node)
    {
        if(this._exp6_ != null)
        {
            this._exp6_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp6_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._exp5_)
            + toString(this._diviser_)
            + toString(this._exp6_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exp5_ == child)
        {
            this._exp5_ = null;
            return;
        }

        if(this._diviser_ == child)
        {
            this._diviser_ = null;
            return;
        }

        if(this._exp6_ == child)
        {
            this._exp6_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._exp5_ == oldChild)
        {
            setExp5((PExp5) newChild);
            return;
        }

        if(this._diviser_ == oldChild)
        {
            setDiviser((TDiviser) newChild);
            return;
        }

        if(this._exp6_ == oldChild)
        {
            setExp6((PExp6) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
