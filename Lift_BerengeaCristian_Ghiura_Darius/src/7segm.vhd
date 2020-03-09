library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.all;
use IEEE.STD_LOGIC_ARITH.all;

entity E_7segm is 
	port (binar:in std_logic_vector (3 downto 0);  
	seg0,seg1: out std_logic_vector (0 to 6));
end E_7segm;   

architecture AE_7segm of E_7segm is
begin 
	process (binar)
	begin 
		case binar is
        when "0000" => seg0 <= "0000001"; --0  
		seg1<="1111111";
		when "0001" => seg0 <= "1001111"; --1
		seg1<="1111111";  
        when "0010" => seg0 <= "0010010"; --2
		seg1<="1111111";
        when "0011" => seg0 <= "0000110"; --3
		seg1<="1111111";
        when "0100" => seg0 <= "1001100"; --4
		seg1<="1111111";
        when "0101" => seg0 <= "0100100"; --5 
		seg1<="1111111";
        when "0110" => seg0 <= "0100000"; --6
		seg1<="1111111";
        when "0111" => seg0 <= "0001111"; --7
		seg1<="1111111";		
        when "1000" => seg0 <= "0000000"; --8
		seg1<="1111111";
        when "1001" => seg0 <= "0000100"; --9
		seg1<="1111111";
		when "1010" => seg0 <= "0000001"; --10
		seg1<="1001111";
		when "1011" => seg0 <="1001111";  --11
		seg1<="1001111";
		when "1100" => seg0 <="0010010";  --12
		seg1<="1001111";   
		when others => seg0 <= "1111111";
		seg1<="1111111";
		end case;
	end process;
end AE_7segm;