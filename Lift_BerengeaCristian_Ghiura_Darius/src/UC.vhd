library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;

entity UC is
	port (
	OK,OKU,OKC,CLK:in std_logic;
	NR: in std_logic_vector (3 downto 0);
	COM: in std_logic_vector (3 downto 0);
	sens,opreste,repaus: out std_logic);
end UC;

architecture AUC of UC is
signal a_sens: std_logic := '0' ;
begin	 
   process (CLK,OK,OKU,OKC) 
   variable v: natural;
   variable v1: natural;  
   variable e_h: integer :=-1; 
   variable e_l: integer :=13; 	
   variable urcare: std_logic_vector (0 to 12):= "0000000000000";	
   variable coborare: std_logic_vector (0 to 12):= "0000000000000";	
   begin 
	   v:= CONV_INTEGER(NR);
	   v1:= CONV_INTEGER(COM);	
if (OKC = '1') or (OK = '1') or (OKU = '1') then 
if  (OKC = '1')  then -- adaugare comanda	
	  coborare(v1):='1';
	  repaus<='1';
	  if (e_l=13) and (e_h=-1) and (a_sens = '1') and (v1>v) then a_sens<='0'; end if;
end if;
if  (OK = '1')  then -- adaugare comanda

		 if (v1<v) then coborare(v1):='1';
		 else urcare(v1):='1'; end if;

	  repaus<='1';
	  
	  if (e_l=13) and (e_h=-1) and (a_sens = '1') and (v1>v) then a_sens<='0'; end if;
end if;
if  (OKU = '1')  then -- adaugare comanda
	 urcare(v1):='1';
	  repaus<='1';
	  
	  if (e_l=13) and (e_h=-1) and (a_sens = '1') and (v1>v) then a_sens<='0'; end if;
end if;
else
if urcare(12)='1' or coborare(12)='1' then e_h:=12; 
		elsif urcare(11)='1' or coborare(11)='1' then e_h:=11;
		elsif urcare(10)='1' or coborare(10)='1' then e_h:=10;
		elsif urcare(9)='1' or coborare(9)='1' then e_h:=9;
		elsif urcare(8)='1' or coborare(8)='1'	then e_h:=8;
		elsif urcare(7)='1' or coborare(7)='1' then e_h:=7;
		elsif urcare(6)='1' or coborare(6)='1'	then e_h:=6;
		elsif urcare(5)='1' or coborare(5)='1'	then e_h:=5;
		elsif urcare(4)='1' or coborare(4)='1' then e_h:=4;
		elsif urcare(3)='1' or coborare(3)='1' then e_h:=3;
		elsif urcare(2)='1' or coborare(2)='1'	then e_h:=2;
		elsif urcare(1)='1' or coborare(1)='1' then e_h:=1;
		elsif urcare(0)='1' or coborare(0)='1' then e_h:=0;
		else e_h:=-1;
		end if;
			
		if urcare(0)='1' or coborare(0)='1' then e_l:=0; 
		elsif urcare(1)='1' or coborare(1)='1' then e_l:=1;
		elsif urcare(2)='1' or coborare(2)='1' then e_l:=2;
		elsif urcare(3)='1' or coborare(3)='1' then e_l:=3;
		elsif urcare(4)='1' or coborare(4)='1' then e_l:=4;
		elsif urcare(5)='1' or coborare(5)='1' then e_l:=5;
		elsif urcare(6)='1' or coborare(6)='1' then e_l:=6;
		elsif urcare(7)='1' or coborare(7)='1' then e_l:=7;
		elsif urcare(8)='1' or coborare(8)='1' then e_l:=8;
		elsif urcare(9)='1' or coborare(9)='1' then e_l:=9;
		elsif urcare(10)='1' or coborare(10)='1' then e_l:=10;
		elsif urcare(11)='1' or coborare(11)='1' then e_l:=11;
		elsif urcare(12)='1' or coborare(12)='1' then e_l:=12;
		else e_l:=13; 
		end if;

if (CLK = '0') and (CLK'EVENT) then	 
	  
	if (a_sens = '0') then 
		
		if urcare(v) = '1' or v=e_h then  opreste <= '1';
           else opreste <= '0'; end if;
		  urcare(v):='0';
		
	  if (v >= e_h) then a_sens <= '1'; coborare(v):='0'; end if;
	  if (e_l=13) and (e_h=-1) then  a_sens <= '1'; coborare(v):='0'; end if;
	else   
		
		if coborare(v) = '1' or v=e_l then  opreste <= '1';
        else opreste <= '0'; end if;
		  coborare(v):='0';	 
		  
		  
		if (v = e_l) and (e_l<e_h)  then a_sens <= '0'; urcare(v):='0';
		elsif (e_h = e_l) and (v < e_l) then a_sens <= '0'; urcare(v):='0';
		end if;
    end if;	
	 
	 if(v=0) and (e_l<13) and (a_sens = '1') then a_sens<='0'; end if; 
	
end if;
         if (e_l=13) and (e_h=-1) and (CLK = '0') and (v = 0)	-- stabilire stare de repaus, parter
		 	then repaus<='0'; a_sens <= '0'; opreste <= '1'; 
		 else repaus<='1';
	    	end if;	

end if;
	end process; 
	sens <= a_sens;
end AUC;
