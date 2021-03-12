bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf,scanf,gets,getchar              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll  
import printf msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll                           ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import gets msvcrt.dll 
import getchar msvcrt.dll
; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
s resb 20
d resb 20
c dd 0
afisarecarcater db 'Cititi caracterul= ',0
formatcaracter db '%c',0
afisare db 'Cititi sirul s= ',0
citire db 'Ati citit sirul %s',0
afisaresirfinal db 'Sirul dvs este %s',0
newline db 10,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    push dword afisare
    call [printf]
    add esp,4*1
    
    push dword s
    call [gets]
    add esp,4*1
    
    push dword s
    push citire
    call [printf]
    add esp,4*2
    
    push dword newline
    call [printf]
    add esp,4*1
    
    push dword afisarecarcater
    call [printf]
    add esp,4*1
    
    push dword c
    push dword formatcaracter
    call [scanf]
    add esp,4*2
    
    
    
    mov esi,0
    mov edi,0
    mov ecx,20
    repeta:
        mov al,[s+esi]
        cmp al,'a'
        JGE continua
        JL verif2
            
            continua:
            cmp al,'z'
            JLE schimba
            JG verif2
    
    
    
                schimba:
                mov al,[c]
                mov [d+edi],al
                inc edi
                jmp final
                
                        
        verif2:
        mov al,[s+esi]
        cmp al,'A'
        JGE continua2
        JL final
            
            continua2:
            cmp al,'Z'
            JLE schimba2
            JG final
    
                schimba2:
                mov [d+edi],al
                inc edi
                jmp final
              
    
    final:
    inc esi
    loop repeta
    
    push dword d
    push dword afisaresirfinal
    call [printf]
    add esp,4*2
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
