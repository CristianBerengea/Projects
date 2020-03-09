library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity usad is
	port(
	nr6: in std_logic_vector(2 downto 0);
	nr8: in std_logic_vector(3 downto 0); 
	SU,SG,opreste,viteza: in std_logic;
	usadeschisa: out std_logic);
end usad;	

architecture ausad of usad is
begin 
	process(nr6,nr8,SG,SU,opreste)
	variable o:std_logic:='0';
	begin
if(opreste = '1') then
	if(viteza = '0') then
	 if ((nr6<"101") and (nr6>"001")) or (SG = '0') or (SU = '0') then o:='1';
		else o:='0';end if;
	else 
	if ((nr8<"101") and (nr8>"001")) or (SG = '0') or (SU = '0') then o:='1';
		else o:='0';end if;	
	end if;
else o:='0';
end if;
	usadeschisa<=o;
	end process;
end ausad;