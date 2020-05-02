import java.util.ArrayList;

public class SnakeThing extends PokeThing{

	public SnakeThing()
	{
		super("snake", "", 0);
		setDirection(Direction.WEST);
		snakeList = new ArrayList<SnakeBody>();
		snakeLen = 0;
	}
	
	private int snakeLen;
	private ArrayList<SnakeBody> snakeList;
	
	
	public void step()
	{
		Location nextLoc = getDirection().getNextLocation(getLocation());
		if(getBoard().thingAt(nextLoc) instanceof SnakeBody || !nextLoc.isValid(getBoard())) getBoard().newGame(true);
		move();
		addBody();
		checkRemove();
		checkEat();
	}
	
	public void checkEat()
	{
		Location nextLoc = getDirection().getNextLocation(getLocation());
		SnakeBoard b = (SnakeBoard) getBoard(); 
		if(b.thingAt(nextLoc) instanceof FoodThing) {
			b.replaceFood(nextLoc); 
			grow();
		}
	}
	
	public void addBody()
	{
		SnakeBody snake = new SnakeBody();
		snake.move(getLocation());
		getBoard().add(snake, getLocation());
		snakeList.add(snake);
	}
	
	public void grow()
	{
		
		Location loc = getDirection().getNextLocation(getLocation());
		SnakeBody snake = new SnakeBody();
		snake.setLocation(loc);
		getBoard().add(snake, loc);
		snakeList.add(snake);
		move();
		snakeLen++;
	}
	
	public void checkRemove()
	{
		SnakeBody snake = snakeList.remove(0);
		getBoard().removeFromStack(SnakeBody.class, snake.getLocation());
	}
	
	
} 


