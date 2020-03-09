library IEEE;
use IEEE.STD_LOGIC_1164.all;
entity mux41 is
	port (I0,I1,I2,I3,S0,S1:in std_logic;
	Q: out std_logic);
end mux41;

architecture Amux41 of mux41 is
begin
	process(I0,I1,I2,I3,S0,S1)
	begin 
		if (S0 = '0') and (S1 = '0') then Q<=I0;
		elsif (S0 = '1') and (S1 = '0') then Q<=I1;
		elsif (S0 = '0') and (S1 = '1') then Q<=I2;
		else  Q<=I3;
		end if;
	end process;
end Amux41;