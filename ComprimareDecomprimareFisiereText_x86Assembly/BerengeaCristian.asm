.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem msvcrt.lib, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern fopen: proc
extern fprintf: proc
extern fscanf: proc
extern fclose: proc
extern printf: proc
extern scanf: proc
extern fgets: proc
extern strtok: proc
extern strcmp: proc
extern tolower: proc
extern fputs: proc
extern isalpha: proc
extern isdigit: proc
extern exit: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
filename db 500 DUP(0)
;filename db "fisier.txt", 0
filename1 db "dictionar.txt", 0
filename2 db "comprimat.txt", 0
filename3 db "decomprimat.txt", 0
sep db ".,!?;: ",13,10,0
read db "r", 0
update db "a", 0
write db "a", 0
sir db 500 DUP(0)
cuv db 500 DUP(0)
cuv1 db 500 DUP(0)
cuv2 db 500 DUP(0)
format db "%s %d",13, 0
formatt db "%s %d",13, 0
form db "%d", 0
form1 db "%s ",0
form2 db "%s",0
form3 db "%s %s",0
t db "MaDaMaDa",0
f db "%d %d %d %d %d %d",13,10,0
linie db 500 DUP(0)
caracter dd "c%",0
nl db 13,10,0
n dd 0
adr dd 0
ms1 db "1 criptare",13,10,"2 decriptare",13,10,"Introduceti o cifra (1,2)",13,10,0
ms2 db "Introduceti calea absoluta a fisirului",13,10,0
op db 0
.code
scrie proc ; scrie(char * str) ;adauga un cuvant in dictionar
	push EBP
	mov EBP, ESP
	push ebx
	push eax
	push esi
	push edx
	push ecx

	push offset update
	push offset filename1
	call fopen
	add esp, 8
	mov esi, eax
	
	mov ebx,[ebp+8]	; cuvant insertat in dictionar
	mov edx,[ebp+12] 
	;int fprintf(FILE * stream, const char * format, ...);
	push edx
	push ebx
	push offset formatt
	push esi
	call fprintf
	add esp,16
	
	push esi 
	call fclose
	add esp, 4
	
	pop ecx
	pop edx
	pop esi
	pop eax
	pop ebx
	mov ESP, EBP
	pop EBP
	ret 8
scrie endp

lowercase proc ; transforma toate literele cuvantului primit ca paramtru in litere mici
	push EBP
	mov EBP, ESP
	push esi
	push edx
	push eax
	push ebx
	push ecx
	
	xor esi,esi ;nr de caractere din string
	xor edx,edx
	
    mov ebx,[ebp+8]	
	;parcurg stringul
	parcurgere:
	xor edx,edx
	mov dl,[esi+ebx]
	
	push edx
	call tolower
	add esp,4
	
	mov [esi+ebx],al
	inc esi
	mov eax,0
	cmp [esi+ebx],eax
	jne parcurgere
	
	pop ecx
	pop ebx
	pop eax
	pop edx
	pop esi
	mov ESP, EBP
	pop EBP
	ret 4	
lowercase endp

updatedictionar proc  ;; daca cuvantul nu este gasit in dictionar il adauga
	push EBP
	mov EBP, ESP
	push eax
	push ebx
	push ecx
	push edx
	push esi
	push edi

	push offset read
	push offset filename1
	call fopen
	add esp, 8
	mov esi, eax
	
	mov ebx,[ebp+8] ; cuvant cautat in dictionar
	
	mov edi,0
	bucla:
	push offset n
	push offset cuv
	push offset format
	push esi
	call fscanf
	add esp,16
	
	cmp eax,1
	jl gata
	;int strcmp ( const char * str1, const char * str2 );
	push ebx
	push offset cuv
	call strcmp
	add esp,8
	cmp eax,0
	je final
	
	inc edi
	jmp bucla
	gata:
	
	push esi 
	call fclose
	add esp, 4
	
	;;;;;;;;;;;;;;;;;;;;;;;;;
	push edi
	push ebx 
	call scrie
	jmp final1
	
	final:
	push esi 
	call fclose
	add esp, 4
	
	final1:
	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	pop eax
	mov ESP, EBP
	pop EBP
	ret 4

updatedictionar endp

parcurgerelinie proc ; parcurge un string separand cuvintele cu strtok si face update in fisierul dictionar
	push EBP
	mov EBP, ESP
	push ebx
	push esi
	push eax
	push edx
	push ecx
	
	push offset sep 
	push offset sir
	call strtok
	add esp,8
	
	whilee:
	cmp eax,0
    je iesire
	
	mov ebx, eax
	
	push ebx
	call lowercase
	
	push ebx 
	call updatedictionar
	
    	
    push offset sep
	push 0
	call strtok
	add esp,8
	
	jmp whilee
	iesire:
	pop ecx
	pop edx
	pop eax
	pop esi
	pop ebx
	mov ESP, EBP
	pop EBP
	ret 	
parcurgerelinie endp

citire proc ; citeste cate o linie din fisier si o prelucreaza apeland parcurgerelinie
	push EBP
	mov EBP, ESP
	push eax
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset read
	push offset filename
	call fopen
	add esp, 8
	mov esi, eax ; salvam pointerul la fisier
	

	nextline:
	push esi
	push 200
	push offset sir
	call fgets ;char * fgets ( char * str, int num, FILE * pf );
	add esp,12
	
	call parcurgerelinie
	
	cmp eax,0
	je sfarsit
	
	jmp nextline
	
	sfarsit:
	
	push esi 
	call fclose
	add esp, 4
	

	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	pop eax
	mov ESP, EBP
	pop EBP
	ret 
citire endp
cod proc ;returneaza codul din dictionar al cuvantului transmis ca parametru
	push EBP
	mov EBP, ESP
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset read
	push offset filename1
	call fopen
	add esp, 8
	mov esi, eax
	
	mov ebx,[ebp+8] ; cuvant cautat in dictionar
	mov edi,0
	bucla:
	push offset n
	push offset cuv1
	push offset format
	push esi
	call fscanf
	add esp,16	
	cmp eax,1
	jl finall
	
	
	;int strcmp ( const char * str1, const char * str2 );
	push ebx
	push offset cuv1
	call strcmp
	add esp,8
	cmp eax,0
	je finall
	inc edi
	jmp bucla
	finall:
	;mov eax,edi
	
	
	push esi 
	call fclose
	add esp, 4
	
	
	
	push esi 
	call fclose
	add esp, 4
	
	mov eax,edi
	
	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	mov ESP, EBP
	pop EBP
	ret 4 
cod endp

criptl proc ; parcurge un string caracter cu caracter,
	push EBP    ;daca caracterul este litera formeaza un cuvant 
	;mov EBP, ESP      ; daca caracterul nu este cuvant afiseaza codul cuvantului format anterior (daca s-a format un cuvant)
	mov adr,esp         ; iar apoi afiseaza caracterul respectiv (acesta poate fi un spatiu sau un semn de punctuatie)
	push eax
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset write
	push offset filename2
	call fopen
	add esp, 8
	mov ebp, eax
	
	
	mov esi,0
	mov edi,0
	
	parcurgere:
	xor edx,edx
	mov dl,linie[esi]
	
	cmp dl,0 
	je iesire
	
	push edx
	call tolower
	add esp,4
	mov edx,eax
	
	mov cuv[edi],dl
	
	inc esi
	inc edi
	
	mov ebx,edx
	push edx
	call isalpha
	add esp,4
	cmp eax,0
	jne parcurgere
	
	dec edi
	mov al,0
    mov cuv[edi],al

	cmp dl,0
	je iesire
	
	cmp edi,0
	je et
	
	mov edi,0
	push offset cuv
    call cod
	
	push eax
	push offset form
	push ebp
	call fprintf
	add esp,12
	
	et:
	push ebx
	push offset caracter
	push ebp
	call fprintf
	add esp,12

	jmp parcurgere
	iesire:
	
	push ebp 
	call fclose
	add esp, 4

	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	pop eax
	mov esp,adr
	;mov ESP, EBP
	pop EBP
	ret 
criptl endp

criptare proc   ; citeste cate o linie din fisier si o cripteaza
	push EBP
	mov EBP, ESP
	push eax
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset read
	push offset filename
	call fopen
	add esp, 8
	mov esi, eax ; salvam pointerul la fisier

	nextline:
	push esi
	push 200
	push offset linie
	call fgets ;char * fgets ( char * str, int num, FILE * pf );
	add esp,12
	
	
	cmp eax,0
	je sfarsit
	
	
	call criptl
	jmp nextline
	
	sfarsit:
	
	push esi 
	call fclose
	add esp, 4

	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	pop eax
	mov ESP, EBP
	pop EBP
	ret 
criptare endp

decod proc    ; returneaza in eax cuvantul care are codul primit ca parametru
	push EBP
	mov EBP, ESP
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset read
	push offset filename1
	call fopen
	add esp, 8
	mov esi, eax
	
	mov ebx,[ebp+8] ; cuvant cautat in dictionar
	mov edi,0
	bucla:
	push offset cuv1
	push offset cuv2
	push offset form3
	push esi
	call fscanf
	add esp,16	
	cmp eax,1
	jl finall
	
	
	;int strcmp ( const char * str1, const char * str2 );
	push ebx
	push offset cuv1
	call strcmp
	add esp,8
	cmp eax,0
	je finall
	inc edi
	jmp bucla
	finall:
	;mov eax,edi
	
	
	push esi 
	call fclose
	add esp, 4
	
	
	
	push esi 
	call fclose
	add esp, 4
	
	mov eax,offset cuv2
	
	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	mov ESP, EBP
	pop EBP
	ret 4 
decod endp

decriptl proc   ; parcurge un string caracter cu caracter, separa pe rand in alt string  numerele (codurile din dictionar)
	push EBP       
	;mov EBP, ESP
	mov adr,esp
	push eax
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset write
	push offset filename3
	call fopen
	add esp, 8
	mov ebp, eax
	
	
	mov esi,0
	mov edi,0
	
	parcurgere:
	xor edx,edx
	mov dl,linie[esi]
	
	cmp dl,0 
	je iesire
	
	mov cuv[edi],dl
	
	inc esi
	inc edi
	
	mov ebx,edx
	push edx
	call isdigit
	add esp,4
	cmp eax,0
	jne parcurgere
	
	dec edi
	mov al,0
    mov cuv[edi],al

	cmp dl,0
	je iesire
	
	cmp edi,0
	je et
	
	mov edi,0
	push offset cuv
    call decod
	
	push eax
	push offset form2
	push ebp
	call fprintf
	add esp,12
	
	et:
	push ebx
	push offset caracter
	push ebp
	call fprintf
	add esp,12

	jmp parcurgere
	iesire:
	
	push ebp 
	call fclose
	add esp, 4

	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	pop eax
	mov esp,adr
	;mov ESP, EBP
	pop EBP
	ret 
decriptl endp

decriptare proc   ; citeste liniile din fisier si le decripteaza
	push EBP
	mov EBP, ESP
	push eax
	push ebx
	push ecx
	push edx
	push esi
	push edi
	
	push offset read
	push offset filename2
	call fopen
	add esp, 8
	mov esi, eax ; salvam pointerul la fisier

	nextline:
	push esi
	push 200
	push offset linie
	call fgets ;char * fgets ( char * str, int num, FILE * pf );
	add esp,12
	
	
	cmp eax,0
	je sfarsit
	
	
	;push offset linie
	;push offset form1
	;call printf
	;add esp,8
	
	
	call decriptl
	jmp nextline
	
	sfarsit:
	
	push esi 
	call fclose
	add esp, 4

	pop edi
	pop esi
	pop edx
	pop ecx
	pop ebx
	pop eax
	mov ESP, EBP
	pop EBP
	ret 
decriptare endp

start:
	push offset ms1
	call printf
	add esp,4
	push offset op
	push offset form
	call scanf 
	add esp,8
	
	mov ebx,0
	mov bl,op
    cmp ebx,1
	jne decomp
	push offset ms2
	call printf
	add esp,4
	push offset filename
	push offset form2
	call scanf
	add esp,8
    call citire
	call criptare
	jmp fin
	decomp:
	call decriptare
	fin:
	
	push 0
	call exit
end start
