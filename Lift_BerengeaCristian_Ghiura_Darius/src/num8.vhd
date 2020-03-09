library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;


entity num8 is
	port (CLK,RESET: in std_logic;
	Q: out std_logic;
	nr:out std_logic_vector(3 downto 0));
end num8;

architecture Anum8 of num8 is  
begin 
	process (CLK,RESET)
	variable Y: STD_LOGIC_VECTOR(3 downto 0) := "0000";
	begin
		if RESET= '0' then Y:="0000";
		end if;
		if (CLK'EVENT) and (CLK = '1')	then Y := Y + "0001";
			if Y="1000" then 
				Q<='1';
				Y:="0000";
			else Q<='0';
			end if;
		end if;
		nr<=Y;
	end process;
end Anum8;
	
		