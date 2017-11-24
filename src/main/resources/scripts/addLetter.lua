-- addLetter.lua
local word = redis.call('HGET', KEYS[1], ARGV[1])
local guessedword = redis.call('HGET', KEYS[1] , ARGV[2])
local newguessed = ""

for i=1, string.len(word), 1 do
	if word:sub(i,i) == ARGV[3] 
	then 
		newguessed = newguessed .. ARGV[3]
	else
		newguessed = newguessed .. guessedword:sub(i,i)
	end
end

redis.call('HSET', KEYS[1], ARGV[2] , newguessed)

return null