require 'rspec'
require 'bowling' 

describe Bowling do
	before do
		@game = Bowling.new
	end

	def roll_many(pins,times)
		#maximun 20 times (2 times per 1 frame)
		times.times do
			@game.roll(pins)
		end
	end

	it 'can roll gutter game' do
		@game.roll(0)
	end
	it 'can roll all 2' do
		roll_many(2,20)
		expect(@game.score).to eq(40)
	end
	it 'can roll a spare' do
		@game.roll(7)
		@game.roll(3)
		@game.roll(5)
		roll_many(3,17)
		expect(@game.score).to eq(71)
	end
	it 'can roll a strike' do
		@game.roll(10)
		@game.roll(5)
		@game.roll(2)
		roll_many(1,16)
		expect(@game.score).to eq(40)
	end
	it 'can roll perfect game' do
		roll_many(10,12)
		expect(@game.score).to eq(300)
	end
	it 'can roll strike in the last frame' do
		roll_many(1,18)
		@game.roll(10)
		@game.roll(3)
		@game.roll(4)
		expect(@game.score).to eq(35)
	end
	it 'can roll spare in the last frame' do
		roll_many(1,18)
		@game.roll(5)
		@game.roll(5)
		@game.roll(2)
		expect(@game.score).to eq(30)
	end
end