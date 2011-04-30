package dataStructure;

import htmlInterfaces.HTMLTablable;
import htmlInterfaces.HTMLTablable.*;

import java.util.Vector;
import database.*;

/**
 * 
 * @author matthiascaenepeel
 * @version2.0
 */

public class Building implements Databasable,HTMLTablable
{
	private String name;
	private Vector<Room> rooms;
	@Deprecated
	private Vector<Building> neighbours;
	
	public Building(String newname)
	{
		name = newname;
		rooms = new Vector<Room>();
		//neighbours =new Vector<Building>();
	}
	
	public Building()
	{
		rooms = new Vector<Room>();
	}
	
	
	@InDatabase
	@TableInput(order=1,text="##Name_Building_Table##")
	public String getName()
	{
		return name;
	}

	@OutDatabase
	public void setName(String name)
	{
		this.name = name;
	}
	
	@InDatabase
	public Vector<Room> getRooms()
	{
		return (Vector<Room>) rooms.clone();
	}
	
	@OutDatabase(Room.class)
	public void setRooms(Vector<Room> rooms)
	{
		this.rooms = (Vector<Room>) rooms.clone();
	}
	
	public void addRooms(Room room)
	{
		rooms.add(room);
	}
	
	public void removeRooms(Room room)
	{
		rooms.remove(room);
	}
	
	//Hier komen de methodes en parameters voor de database.
	
	private ID id;
	
	@Override
	public void setID(ID id)
	{
		this.id=id;
	}
	
	@Override
	public ID getId()
	{
		return id;
	}
	
	@Override
	public String toString() 
	{
		return getName();
	}
}