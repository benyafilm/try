class Bowling
	def initialize
		@score = 0
		@rolls = []
		@frame = 1
	end
	def roll(pins)
		#put each pin into roll array
		@rolls << pins 
	end
	def score 
		i = 0
		while @frame < 11 do
			if @frame == 10
				if spare? i 
					@score += 10 + @rolls[i+2]
					@frame += 1
				elsif strike? i
					@score += 10 + @rolls[i+1] + @rolls[i+2] 
					@frame += 1
				else
					@score += @rolls[i] + @rolls[i+1]
					@frame += 1
				end
			else
				if spare? i 
					@score += 10 + @rolls[i+2]
					i += 2
					@frame += 1
				elsif strike? i
					@score += 10 + @rolls[i+1] + @rolls[i+2]
					#i only move 1 because in the array 10,0 this is strike frame !
					i += 1
					@frame += 1
				else
					@score += @rolls[i] + @rolls[i+1]
					i += 2
					@frame += 1
				end
			end
		end	
		@score
	end
	def spare? i 
		@rolls[i] + @rolls[i+1] == 10
	end
	def strike? i 
		@rolls[i] == 10
	end
	
end