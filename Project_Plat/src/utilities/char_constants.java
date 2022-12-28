package utilities;

public class char_constants {
	public static class Directions
	{
		public static final int Left = 0;
		public static final int Up = 1;
		public static final int Right = 2;
		public static final int Down = 3;
	}
	public static class CharConstants
	{
		public static final int IdleStance = 0;
		public static final int Jumping = 1;
		public static final int Running = 2;
		public static final int Falling = 3;
		public static final int Hurt = 4;
		
		public static int CharAmount(int char_movement)
		{
			switch (char_movement)
			{
			case Running:
				return 6;
			case IdleStance:
				return 5;
			case Jumping:
			case Hurt:
				return 3;
			case Falling:
				return 1;
			default:
				return 1;
			}
		}
	}

}

