library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity div1 is 
	port ( CLK : in std_logic;
	D : out std_logic);
end div1;


architecture Adiv1 of div1 is 	
signal S : std_logic_vector (26 downto 0):= "000000000000000000000000000";
begin 
	process (CLK)
	begin
		if (CLK'EVENT) and (CLK='1') then S<= S+1;
		end if;
		D <= S(26);
	end process;
end Adiv1;
