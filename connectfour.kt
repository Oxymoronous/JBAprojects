package connectfour
fun printEmptyRow(col: Int): MutableList<String> {
    val row: MutableList<String> = mutableListOf()
    for (i in 0..col) {
        row.add("║")
        row.add(" ")
    }
    println(row.joinToString(""))
    return row
}

fun generateBoard(row: Int, column: Int): MutableList<MutableList<String>> {
    val board: MutableList<MutableList<String>> = mutableListOf()        //represents the entire board, is a 2D array

    for (i in 0 until row) {
        val r: MutableList<String> = mutableListOf()
        r.clear()
        for (j in 0..column) { //adding row per row into the 2D board array
            if (j != column) {
                r.add("║")
                r.add(" ")
            } else r.add("║")
        }
        board.add(r)
    }
    val footerRow: MutableList<String> = mutableListOf()
    for (j in 0..column) {
        if (j == 0) {
            footerRow.add("╚")
        } else if (j == column) {
            footerRow.add("═╝")
        } else {
            footerRow.add("═╩")
        }
    }
    board.add(footerRow)
    return board
}

fun printBoard(row: Int, column: Int) {
    // ║, ╚, ═, ╩, ╝,║
    var header = " "
    for (i in 1..column) {
        header = header + "$i "
    }
    println(header)
    for (i in 0..row) {
        if (i != row) {
            val emptyrow = printEmptyRow(column)
        }else {
            for (j in 0..column) {
                if (j == 0) {
                    print("╚")
                }else if (j == column) {
                    print("═╝")
                }else {
                    print("═╩")
                }
            }
            print("\n")
        }
    }
}

fun printBoardImproved(board: MutableList<MutableList<String>>, row: Int, column: Int) {
    var header = " "
    for (i in 1..column) {
        header = header + "$i "
    }
    println(header)
    for (i in 0..row){
        println(board[i].joinToString(""))
    }
}

fun columnToIndex(column: Int): Int {
    //column argument is this function should be definitely within board dimensions
    // 1 2 3 4 ; column
    //101010101; board
    //012345678; index
    val index = column * 2 - 1
    return (index)
}

fun checkWinDiag(x: Int, y: Int, board: MutableList<MutableList<String>>): Boolean {
    var xIndex = x
    var yIndex = columnToIndex(y)
    val current = board[x][yIndex]

    val yLimit = board.first().lastIndex
    val xLimit = board.lastIndex-1

    //go to the right as long as keep seeing same
    var consecutive = 1
    while (true) {
        if (xIndex - 1 >= 0 && yIndex + 2 <= yLimit) {
            xIndex --
            yIndex += 2
            if (board[xIndex][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }
    xIndex = x
    yIndex = columnToIndex(y)
    //go to the left as long as keep seeing same
    while (true) {
        if (xIndex + 1 <= xLimit && yIndex - 2 >= 0) {
            xIndex ++
            yIndex -= 2
            if (board[xIndex][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }

    if (consecutive >= 4) return true
    else consecutive = 1
    xIndex = x
    yIndex = columnToIndex(y)
    //go to the left as long as keep seeing same
    while (true) {
        if (xIndex + 1 <= xLimit && yIndex + 2 <= yLimit) {
            xIndex ++
            yIndex += 2
            if (board[xIndex][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }

    xIndex = x
    yIndex = columnToIndex(y)
    //go to the left as long as keep seeing same
    while (true) {
        if (xIndex - 1 >= 0 && yIndex - 2 >= 0) {
            xIndex --
            yIndex -= 2
            if (board[xIndex][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }

    return consecutive >= 4
}

fun checkWinVer(x: Int, y: Int, board: MutableList<MutableList<String>>): Boolean {
    var xIndex = x
    val yIndex = columnToIndex(y)
    val current = board[x][yIndex]
    val xLimit = board.lastIndex-1

    //go to the bottom as long as keep seeing same
    var consecutive = 1
    while (true) {
        if (xIndex + 1 <= xLimit) {
            xIndex ++
            if (board[xIndex][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }
    xIndex = x
    //go to the top as long as keep seeing same
    while (true) {
        if (xIndex - 1 >= 0) {
            xIndex --
            if (board[xIndex][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }

    val win = consecutive >= 4
    return win
}

fun checkWinHor(x: Int, y: Int, board: MutableList<MutableList<String>>): Boolean {
    var yIndex = columnToIndex(y)
    val current = board[x][yIndex]

    val yLimit = board.first().lastIndex
    val xLimit = board.lastIndex-1

    //go to the right as long as keep seeing same
    var consecutive = 1
    while (true) {
        if (yIndex + 2 <= yLimit) {
            yIndex += 2
            if (board[x][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }

    yIndex = columnToIndex(y)
    //go to the left as long as keep seeing same
    while (true) {
        if (yIndex - 2 >= 0) {
            yIndex -= 2
            if (board[x][yIndex] == current) {
                consecutive++
                if (consecutive >= 4) break
            } else break
        } else {
            break
        }
    }

    val win = consecutive >= 4
    return win
}

fun checkFull(board: MutableList<MutableList<String>>): Boolean {
    return !board.first().contains(" ")
}

fun printScore(firstPlayer: String, secondPlayer: String, firstscore: Int, secondscore: Int, scoretrack: Boolean) {
    if (scoretrack){
        println("Score")
        println("$firstPlayer: $firstscore $secondPlayer: $secondscore")
    }
}

fun flip(mtlist: MutableList<String>): MutableList<String> {
    val rtlist: MutableList<String> = mtlist
    val tmp = mtlist.first()
    rtlist[0] = rtlist[1]
    rtlist[1] = tmp
    return rtlist
}


/*
    Function returns an integer as a representation of who won (or draw)
    0 = draw
    1 = firstplayer won
    2 = secondplayer won
    -1 = someone crashes the game
 */
fun playGame(players: MutableList<String>, row: Int, column: Int, switch: Boolean = false, firstscore: Int = 0, secondscore: Int = 0, scoretrack: Boolean = false): Int {
    var symbolList = mutableListOf("o", "*")
    var turn = 0
    turn = 0

    var move = ""
    val regex = Regex("\\d+")
    val board = generateBoard(row, column)
    symbolList = if (switch) flip(symbolList) else symbolList   //switching the symbolList so that players use consistent piece
    var xMark = 0   // coordinate marker for where the move is being placed
    var yMark = 0   // coordinate marker ~~~
    var fs = firstscore
    var ss = secondscore
    while (true) {
        var legalMove = false      //check whether a move has been done
        turn %= 2
        println("${players[turn]}'s turn")
        move = readLine()!!
        if (move == "end") {
            println("Game over!")
            return -1
        }
        if(!move.matches(regex)) { //illegal input that is not an integer
            println("Incorrect column number")
        } else {
            if (move.toInt() > column || move.toInt() <= 0) {
                //column entered is out of bounds
                println("The column number is out of range (1 - $column)")
                continue
            } else {
                //user input column is legal, only need to check full or not
                val index = columnToIndex(move.toInt())
                for (i in row-1 downTo 0) {
                    //loop through board rows to check whether possible to insert piece
                    if (board[i][index] == " ") {
                        board[i][index] = symbolList[turn]
                        legalMove = true
                        xMark = i
                        yMark = move.toInt()
                        break
                    }
                }
                if (legalMove) {
                    printBoardImproved(board, row, column)
                    //checking winning/draw conditions
                    if (checkWinHor(xMark, yMark, board) || checkWinDiag(xMark, yMark, board) || checkWinVer(xMark, yMark, board)) {
                        println("Player ${players[turn]} won")
                        if (switch) {
                            if (turn == 0) ss += 2 else fs += 2
                        } else {
                            if (turn == 0) fs += 2 else ss += 2
                        }    //if turns are switched, turn = 0 represents the originally second player
                        val flipPlayList = if (switch) flip(players) else players
                        printScore(flipPlayList[0], flipPlayList[1], fs, ss, scoretrack)        //only prints score updates when the game finishes
                        if (switch) {
                            turn = when (turn) {
                                0 -> 2
                                1 -> 1
                                else -> turn
                            }
                        } else {
                            turn += 1
                        }
                        /*
                        if switch is true
                            turn = 0 win means player 2 win
                            turn = 1 means player 1 win
                        and vice versa
                         */
                        return turn
                    }
                    if (checkFull(board)) {
                        //board is full in this case
                        println("It is a draw")
                        val flipPlayList2 = if (switch) flip(players) else players
                        fs++
                        ss++
                        printScore(flipPlayList2[0], flipPlayList2[1], fs, ss, scoretrack)
                        return 0
                    }
                    turn++
                } else {
                    println("Column $move is full")
                }
            }
        }
    }
}

fun singleOrMultiple(): Int {
    println("Do you want to play single or multiple games?")
    println("For a single game, input 1 or press Enter")
    println("Input a number of games:")
    val input = readLine()!!
    val regex = Regex("\\d+")
    return if (input.matches(regex) ) {
        //check whether input is a strictly positive digit
        var check = input.toInt()
        if (check <= 0) {
            check = -1
            println("Invalid input")
        }
        check
    }
    else if (input == "") 1
    else {
        println("Invalid input")
        -1
    }
}

fun main() {
    println("Connect Four")
    println("First player's name:")
    val firstplayer = readLine()!!
    println("Second player's name:")
    val secondplayer = readLine()!!
    var playersList = mutableListOf(firstplayer, secondplayer)
    var checkinput = false
    val regex = Regex("\\d+x\\d+")
    var row = 0
    var col = 0
    while (!checkinput) {
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        var row_bool = true
        var col_bool = true
        val dimension = readLine()!!.replace("\\s".toRegex(),"").replace("X","x")
        if (dimension == "") {
            row = 6
            col = 7
            checkinput = true
            break
        }
        if (dimension.matches(regex)) {
            val r:String = dimension.split("x")[0]
            val c:String = dimension.split("x")[1]
            if (r.toInt() !in 5..9) {
                row_bool = false
            }
            if (c.toInt() !in 5..9) {
                col_bool = false
            }
            if (!row_bool) {
                println("Board rows should be from 5 to 9")
            }
            if (!col_bool) {
                println("Board columns should be from 5 to 9")
            }
            if (row_bool && col_bool) {
                row = r.toInt()
                col = c.toInt()
                checkinput = true
            }
        }
        else {
            println("Invalid input")
        }
    }
    var numOfGames = -1
    while (numOfGames == -1) {
        numOfGames = singleOrMultiple()
    }
    println("$firstplayer VS $secondplayer")
    println("$row X $col board")
    var switch = false
    var scoretrack = false
    if (numOfGames == 1) {
        println("Single game")
        printBoard(row, col)
        val singlegame = playGame(playersList, row, col)
        return
    } else {
        println("Total $numOfGames games")
        scoretrack = true
    }

    var state = 1
    var firstScore = 0
    var secondScore = 0
    for (gameNum in 1..numOfGames) {
        switch = if (gameNum % 2 == 1) false else true
        playersList = if (switch) flip(playersList) else playersList
        println("Game #$gameNum")
        printBoard(row, col)
        state = playGame(playersList, row, col, switch, firstScore, secondScore, scoretrack)
        when (state) {
            0 -> {
                firstScore++
                secondScore++
            }
            1 -> firstScore += 2
            2 -> secondScore += 2
            else -> break           //this is the terminating condition when either player brutally ends the game
        }
    }
    println("Game over!")
}