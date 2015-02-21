package predicates;

import common.Artist;
import common.Predicate;

public class MissingInfo implements Predicate {

	@Override
	public boolean match(Artist artist) {
		// TODO Auto-generated method stub
		
		if(artist.location==null || artist.songs==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	
}
