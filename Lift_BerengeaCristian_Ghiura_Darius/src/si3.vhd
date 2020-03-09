library IEEE;
use IEEE.STD_LOGIC_1164.all;
entity si3 is
	port(A,B,C: in std_logic;
	Y: out std_logic);
end si3;

architecture Asi3 of si3 is
signal X: std_logic;
begin
	X<= A and B;
    Y <= X and C;
end Asi3;