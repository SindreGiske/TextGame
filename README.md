run main.kt in to try game. The game is 100% run in the command line. 

There is currently no support for any command not mentioned in the helptext. 

Commands :
- go [direction]
- unlock [door direction]
- inspect [asset]
- inspect door [direction]
- take [item] from [asset]
- use [item] on [asset]
- interact [asset]
- inventory
- look              (looks around the room)
- look at [asset]   (same as inspect [asset])
- quit

MOVEMENT :

go works in cardinal directions [north, south, east, west].

go command now also supports [go back] which returns you to the last room you left.

TAKE ITEM: 

If you have inspected the asset which holds the item (and not left the room since) 
you can simply say for example [take key], and the key will be taken from the table, 
not needing to specify [take key from table]. 

UNLOCK DOOR : 

If the room only has one door, you can unlock it with just [ unlock door ]. [ use key on door ] also works.

NPC DIALOGUE : 

In Level 2 you will meet NPCs. When you enter the room in which they are staying the dialogue will start 
after your next input, whatever that may be. 

The dialogue system gives you a numbered list of responses. input the number of the 
response you want to give, and in turn the NPC will respond accordingly. 
Dialogue is slightly time staggered to feel more "human". 