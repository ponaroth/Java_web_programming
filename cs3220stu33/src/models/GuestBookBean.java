package models;

import java.util.ArrayList;
import java.util.List;

public class GuestBookBean {

    List<GuestBookEntryB> entries;

    public GuestBookBean()
    {
        entries = new ArrayList<GuestBookEntryB>();
    }

    public void setAddEntry( String dummy )
    {
        GuestBookEntryB entry = new GuestBookEntryB();
        entries.add( entry );
    }

    public GuestBookEntryB getLastEntry()
    {
        return entries.get( entries.size() - 1 );
    }

    public List<GuestBookEntryB> getEntries()
    {
        return entries;
    }

    public void setEntries( List<GuestBookEntryB> entries )
    {
        this.entries = entries;
    }

}
