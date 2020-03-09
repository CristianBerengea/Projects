library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity rnum4 is
	port(CLKK,M: in std_logic;
	     Q: out std_logic_vector (3 downto 0));
end rnum4;

architecture rnr4 of rnum4 is
signal Y: STD_LOGIC_VECTOR(3 downto 0) := "0000";
begin
	process(CLKK)
	begin
		if (CLKK'EVENT) and (CLKK = '1') then
			if(M = '0') then Y <= Y + "0001";
		    else Y <= Y - "0001"; end if;
	    end if;
	end process;
	Q <= Y;
end rnr4;