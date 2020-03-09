library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity divAfis is 
	port (CLK: in std_logic;
	D: out std_logic_vector(2 downto 0));
end divAfis;


architecture AdivAfis of divAfis is 	
signal S : std_logic_vector (18 downto 0):= "0000000000000000000";
begin 
	process (CLK,S)
	begin
		if (CLK'EVENT) and (CLK='1') then S<= S+1;
		end if;
		D(0)<= S(16);
		D(1)<= S(17);
		D(2)<= S(18);
	end process;
end AdivAfis;
