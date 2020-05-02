
public class SnakeBoard extends Board{
	
	private static final String UP = "UP";
	private static final String DOWN = "DOWN";
	private static final String LEFT = "LEFT";
	private static final String RIGHT = "RIGHT";
	
	private SnakeThing s;
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Complete the methods here
	
	// Get any random location on the board
	public Location getRandomLocation()
	{
		while(true) {
			Location loc = new Location();
			loc.setRow((int) (Math.random() * getRows()));
			loc.setColumn((int) (Math.random() * getColumns()));
			if(thingAt(loc).isBlank()) {
				return loc; 
			}
		}
	}
		
	// Get any random location on the board that is not in the location sent in the parameter
	// used by init() to place the first food somewhere not on top of the snake
	public Location getRandomLocation(Location notHere)
	{
		while(true) {
			Location loc = getRandomLocation();
			if(loc.compareTo(notHere) != 0) {
				return loc; 
			}
		}
	}
		
	// Places food anywhere
	public void randomFood()
	{
		FoodThing food = new FoodThing();
		food.setLocation(getRandomLocation());
		add(food);
	}
		
	// Places food somewhere other than the location sent in
	public void randomFood(Location notHere)
	{
		FoodThing food = new FoodThing();
		food.setLocation(getRandomLocation(notHere));
		add(food);
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// You do not need to modify any of the following methods
	//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	// Create a board for Snake
	public SnakeBoard() 
	{
	 	super(20, 20);
	 	newGame(false);
	}
	
	// Do this on start or reset
	public void newGame(boolean repaint)
	{
		super.newGame(repaint);
		
		init();
	}
	
	// Create a board
	public void init()
	{		
		Location snakeLoc = new Location(getRows() / 2, getColumns() / 2);
		randomFood(snakeLoc);
		s = new SnakeThing();
		add(s, snakeLoc);
	}
	
	// Replaces the current food in the board
	public void replaceFood(Location loc)
	{
		removeFromStack(FoodThing.class, loc);
		randomFood();
	}
	
	// Deal with key presses
	@Override
	public boolean keyPressed(String description)
    {
		if (description.equals(UP))
		{
			s.setDirection(Direction.NORTH);
		}
		else if (description.equals(DOWN))
		{
			s.setDirection(Direction.SOUTH);
		}
		else if (description.equals(LEFT))
		{
			s.setDirection(Direction.WEST);
		}
		else if (description.equals(RIGHT))
		{
			s.setDirection(Direction.EAST);
		}

        return false;
    }
	
	
		
	
	
	// More efficient step() -- only processes step if the location is not blank.
	public void step()
	{
		for (int i = 0; i < getRows(); i++)
		{
			for (int j = 0; j < getColumns(); j++)
			{
				if (false == things[i][j].get().getRepaint())
				{
					if (false == thingAt(i, j).isBlank())
					{
						things[i][j].get().setRepaint(true);
						things[i][j].get().step();
					}
				}
			}
		}
		for (int i = 0; i < getRows(); i++)
		{
			for (int j = 0; j < getColumns(); j++)
			{
				things[i][j].get().setRepaint(false);
			}
		}
		repaint();
	}
}
