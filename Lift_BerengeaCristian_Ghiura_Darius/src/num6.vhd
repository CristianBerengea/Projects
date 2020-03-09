library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;


entity num6 is
	port (CLK,RESET: in std_logic;
	Q: out std_logic;
	nr:out std_logic_vector(2 downto 0));
end num6;

architecture Anum6 of num6 is  
begin 
	process (CLK,RESET)
	variable Y: STD_LOGIC_VECTOR(2 downto 0) := "000";
	begin
		if RESET= '0' then Y:="000"; end if;
		if (CLK'EVENT) and (CLK = '1')	then Y := Y + "001";
		if Y="110" then 
			Q<='1';
			Y:="000";
		else Q<='0';
		end if;
		end if;
		nr<=Y;
	end process;
end Anum6;
	
		