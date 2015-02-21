package predicates;

import common.Artist;
import common.Predicate;

public class FindSong implements Predicate {

	String song;
	
	public FindSong(String song)
	{
		this.song=song;
	}
	
	@Override
	public boolean match(Artist artist) {
		// TODO Auto-generated method stub
		boolean bool=false;
		for(int i=0;i<artist.songs.length;i++)
		{
			if((artist.songs[i]).equals(song))
			{
				return true;
			}
			
		}
		
		return false;
	}

}
