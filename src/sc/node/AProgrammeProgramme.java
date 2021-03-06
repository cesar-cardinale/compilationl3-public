/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AProgrammeProgramme extends PProgramme
{
    private PListedecvaropt _listedecvaropt_;
    private PListedecfonc _listedecfonc_;

    public AProgrammeProgramme()
    {
        // Constructor
    }

    public AProgrammeProgramme(
        @SuppressWarnings("hiding") PListedecvaropt _listedecvaropt_,
        @SuppressWarnings("hiding") PListedecfonc _listedecfonc_)
    {
        // Constructor
        setListedecvaropt(_listedecvaropt_);

        setListedecfonc(_listedecfonc_);

    }

    @Override
    public Object clone()
    {
        return new AProgrammeProgramme(
            cloneNode(this._listedecvaropt_),
            cloneNode(this._listedecfonc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAProgrammeProgramme(this);
    }

    public PListedecvaropt getListedecvaropt()
    {
        return this._listedecvaropt_;
    }

    public void setListedecvaropt(PListedecvaropt node)
    {
        if(this._listedecvaropt_ != null)
        {
            this._listedecvaropt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listedecvaropt_ = node;
    }

    public PListedecfonc getListedecfonc()
    {
        return this._listedecfonc_;
    }

    public void setListedecfonc(PListedecfonc node)
    {
        if(this._listedecfonc_ != null)
        {
            this._listedecfonc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listedecfonc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._listedecvaropt_)
            + toString(this._listedecfonc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._listedecvaropt_ == child)
        {
            this._listedecvaropt_ = null;
            return;
        }

        if(this._listedecfonc_ == child)
        {
            this._listedecfonc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._listedecvaropt_ == oldChild)
        {
            setListedecvaropt((PListedecvaropt) newChild);
            return;
        }

        if(this._listedecfonc_ == oldChild)
        {
            setListedecfonc((PListedecfonc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
