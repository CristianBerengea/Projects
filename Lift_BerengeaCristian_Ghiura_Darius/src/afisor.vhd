library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity afisor is
	port (CLK : in std_logic_vector(2 downto 0);
	NR: in std_logic_vector(3 downto 0);
	sens,opreste,repaus,SU,SG,D,viteza: in std_logic;
	COM: in std_logic_vector(3 downto 0);
	ANOD: out std_logic_vector(7 downto 0);
	CATOD: out std_logic_vector(0 to 6));
end afisor;

architecture Aafisor of afisor is
component E_7segm is 
	port (binar:in std_logic_vector (3 downto 0);  
	seg0,seg1: out std_logic_vector (0 to 6));
end component;

signal 	A0 :std_logic_vector(0 to 6);
signal 	A1 :std_logic_vector(0 to 6);
signal 	C0 :std_logic_vector(0 to 6);
signal 	C1 :std_logic_vector(0 to 6);

begin
	E0: E_7segm port map(NR,A0,A1);
	E1: E_7segm port map(COM,C0,C1);
	process (CLK,A0,A1)
	begin
		if (CLK = "000") then ANOD <= "11111110"; CATOD <= A0;
	   elsif (CLK = "001") then ANOD<="11111101"; CATOD <= A1;
		elsif (CLK = "010") then ANOD<="11101111"; CATOD <= C0;
		elsif (CLK = "011") then  ANOD<="11011111"; CATOD <= C1;
		elsif (CLK = "100") then ANOD<="10111111";	
			if (opreste = '1') then
				if (SG = '0') or (SU = '0') then
					CATOD<="0110000";
			    else  
					CATOD<="1111111";
				end if;	 
			else CATOD<="1111111";
			end if;
		elsif (CLK = "101") then ANOD<="11111011"; 
			if(D = '1') or (repaus = '0') then CATOD<="1100011";
			else CATOD<="1111111"; end if;
		elsif (CLK = "110") then ANOD<="01111111"; 
			if(viteza = '0') then CATOD<="1001111";
			else CATOD<="0000110"; end if;
		else  ANOD<="11110111"; 
            if(sens = '0') then CATOD<="1000001";
			else CATOD<="0110001"; end if;	
		end if;
	end process;
end Aafisor;
	