library IEEE;
use IEEE.STD_LOGIC_1164.all;
entity si2 is
	port(A,B: in std_logic;
	Y: out std_logic);
end si2;

architecture Asi2 of si2 is
begin	
Y <= A and B;
end Asi2;