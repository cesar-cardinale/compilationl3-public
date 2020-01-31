/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstructionEcrireInstruction extends PInstruction
{
    private PInstructionEcrire _instructionEcrire_;

    public AInstructionEcrireInstruction()
    {
        // Constructor
    }

    public AInstructionEcrireInstruction(
        @SuppressWarnings("hiding") PInstructionEcrire _instructionEcrire_)
    {
        // Constructor
        setInstructionEcrire(_instructionEcrire_);

    }

    @Override
    public Object clone()
    {
        return new AInstructionEcrireInstruction(
            cloneNode(this._instructionEcrire_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstructionEcrireInstruction(this);
    }

    public PInstructionEcrire getInstructionEcrire()
    {
        return this._instructionEcrire_;
    }

    public void setInstructionEcrire(PInstructionEcrire node)
    {
        if(this._instructionEcrire_ != null)
        {
            this._instructionEcrire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instructionEcrire_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._instructionEcrire_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._instructionEcrire_ == child)
        {
            this._instructionEcrire_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._instructionEcrire_ == oldChild)
        {
            setInstructionEcrire((PInstructionEcrire) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
