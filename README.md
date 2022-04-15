# Snake
A game where the player maneuvers a snake that becomes an obstacle as it grows from eating apples.

This was a fun project to see if I could replicate the game Snake as best as I could.

##### How to Play
Clone the repo into a directory

`~ git clone git@github.com:Jason1165/Snake.git `

You will need to have [Processing](https://processing.org/) in order to run the files listed [SnakeGame](https://github.com/Jason1165/Snake/tree/main/SnakeGame).

Using WASD you can control the snake and make it grow. Make sure to click on the window that is drawn so that the IDE properly takes in the letter inputs.

##### Unresolved Issues
- No Terminal Version of Snake is available yet
- Snakes can be sped up in one direction
- Colors and shapes are not the best and can be made better
- Inability to tell path of snake when no borders are made (make a dimension slightly smaller)

##### Making
The code works by creating a map where a snake of length is 4 made and each coordinate of the snake is saved into an ArrayDeque. The front of the Deque serves as the head and the back serves as the tail. When the snake moves in a certain direction, the new coordinate is placed into the front of the Deque and the last one removed if the snake did not eat an apple. The middle of the ArrayDeque serves as the body of the snake.

A better implementation of this could be possibly to remove the board and use only the ArrayDeque for the parts of the snake. This however could complicate a toString but the code would be cleaner.

The previous direction is saved so the snake moves in that direction when no move is made. 
