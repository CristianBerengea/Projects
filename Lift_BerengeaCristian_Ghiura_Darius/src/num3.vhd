library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;


entity num3 is
	port (CLK,RESET: in std_logic;
	Q: out std_logic);
end num3;

architecture Anum3 of num3 is  
begin 
	process (CLK,RESET)
	variable Y: STD_LOGIC_VECTOR(1 downto 0) := "00";
	begin
		if RESET= '1' then Y:="00";
		end if;
		if (CLK'EVENT) and (CLK = '1')	then Y := Y + "01";	 
		  if Y="11" then 
			Q<='1';
			Y:="00";
		   else Q<='0';
		  end if;
		end if;
	end process;
end Anum3;
	