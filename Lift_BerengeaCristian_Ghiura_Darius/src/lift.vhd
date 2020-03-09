library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity lift is
	port (Viteza,CLK,SG,SU,OK,OKU,OKC: in std_logic;
    cerere: in std_logic_vector (3 downto 0);
	ANOD: out std_logic_vector(7 downto 0);
	CATOD: out std_logic_vector(0 to 6); 
	repaus: out std_logic;
	opreste: out std_logic);
end lift;

architecture Alift of lift is  

component rnum4 is
	port(CLKK,M: in std_logic;
	     Q: out std_logic_vector (3 downto 0));
end component;	  

component mux41 is
	port (I0,I1,I2,I3,S0,S1:in std_logic;
	Q: out std_logic);
end component;

component si3 is
	port(A,B,C: in std_logic;
	Y: out std_logic);	
end component; 

component num3 is
	port (CLK,RESET: in std_logic;
	Q: out std_logic);
end component; 

component num6 is
	port (CLK,RESET: in std_logic;
	Q: out std_logic;
	nr: out std_logic_vector(2 downto 0));
end component; 

component si2 is
	port(A,B: in std_logic;
	Y: out std_logic);
end component; 

component UC is
	port (
	OK,OKU,OKC,CLK:in std_logic;
	NR: in std_logic_vector (3 downto 0);
	COM: in std_logic_vector (3 downto 0);
	sens,opreste,repaus: out std_logic);
end component;		

component afisor is
	port (CLK : in std_logic_vector(2 downto 0);
	NR: in std_logic_vector(3 downto 0);
	sens,opreste,repaus,SG,SU,D,viteza: in std_logic;
	COM: in std_logic_vector(3 downto 0);
	ANOD: out std_logic_vector(7 downto 0);
	CATOD: out std_logic_vector(0 to 6));
end component;

component divAfis is 
	port ( CLK : in std_logic;
	D : out std_logic_vector(2 downto 0));
end component;

component div1 is 
	port ( CLK : in std_logic;
	D : out std_logic);
end component;

component num8 is
	port (CLK,RESET: in std_logic;
	Q: out std_logic;
	nr: out std_logic_vector(3 downto 0));
end component; 
component usad is
	port(
	nr6: in std_logic_vector(2 downto 0);
	nr8: in std_logic_vector(3 downto 0); 
	SU,SG,opreste,viteza: in std_logic;
	usadeschisa: out std_logic);
end component;

signal S1: std_logic;
signal S2: std_logic;
signal S3: std_logic;
signal S4: std_logic;
signal S5: std_logic; 
signal S8: std_logic;  
signal S9: std_logic := '0';
signal S10: std_logic := '0';
signal S11: std_logic := '0'; 
signal S12: std_logic;
signal Et: std_logic_vector (3 downto 0) := "0000";
signal CLK1: std_logic;	 
signal CLK_afisor: std_logic_vector(2 downto 0);
signal nr6: std_logic_vector(2 downto 0);
signal nr8: std_logic_vector(3 downto 0); 
signal D: std_logic;
begin 	  
	E0: div1 port map (CLK,CLK1);
	E1: si2 port map(CLK1,S11,S8);
	E2: num8 port map(S8,S9,S1,nr8);
	E3: num6 port map(S8,S9,S12,nr6);
	E4: si3  port map(SG,SU,S12,S3);
	E5: si3  port map(SG,SU,S1,S2);
	E6: num3 port map(S8,S5,S4);
	E7: mux41 port map(S8,S3,S4,S2,S9,Viteza,S5);
	E8: rnum4 port map(S5,S10,Et);
	E9: UC port map(OK,OKU,OKC,S5,Et,cerere,S10,S9,S11);
	opreste<=S9;
	repaus<=S11;
   E10: divAfis port map (CLK,CLK_afisor);
	E11: afisor port map (CLK_afisor,Et,S10,S9,S11,SG,SU,D,viteza,cerere,ANOD,CATOD);
	E12: usad port map(nr6,nr8,SU,SG,S9,viteza,D);
end architecture;
