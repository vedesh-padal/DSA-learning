import enum

class GridPosition(enum.Enum):
	EMPTY = 0
	RED = 1
	YELLOW = 2

class Grid:
	def __init__(self, rows, columns):
		self._rows = rows
		self._columns = columns
		self._grid = None
		self.initGrid()

	def initGrid(self):
		self._grid = ([[GridPosition.EMPTY for _ in range(self._columns)] 
				 		for _ in range(self._rows)])
		
	def getGrid(self):
		return self._grid
	
	def getColumnCount(self):
		return self._columns
	
	def placePiece(self, column, piece):
		if column < 0 or column >= self._columns:
			raise ValueError('Invalid column')
		if piece == GridPosition.EMPTY: # necessary becoz of design flaw in ours
			raise ValueError('Invalid piece') 
		
		for row in range(self._rows - 1, -1, -1):
			if self._grid[row][column] == GridPosition.EMPTY:
				self._grid[row][column] = piece
				return row
			
	def checkWin(self, connectN, row, col, piece):
		count = 0
		# check horizontal
		for c in range(self._columns):
			if self._grid[row][c] == piece:
				count += 1
			else:
				count = 0
			if count == connectN:
				return True
		
		count = 0
		# check vertical
		for r in range(self._rows):
			if self._grid[r][col] == piece:
				count += 1
			else:
				count = 0
			if count == connectN:
				return True
			
		# check diagonal
		count = 0
		for r in range(self._rows):
			c = row + col - r
			if c >= 0 and c < self._columns and self._grid[r][c] == piece:
				count += 1
			else:
				count = 0
			if count == connectN:
				return True
			
		# check anti-diagonal
		count = 0
		for r in range(self._rows):
			c = col - row + r
			if c >= 0 and c < self._columns and self._grid[r][c] == piece:
				count += 1
			else:
				count = 0
			if count == connectN:
				return True
		
		return False

class Player:
	def __init__(self, name, pieceColor):
		self._name = name
		self._pieceColor = pieceColor

	def getName(self):
		return self._name

	def getPieceColor(self):
		return self._pieceColor