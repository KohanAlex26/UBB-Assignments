bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    ;8. Se da un sir de dublucuvinte. Sa se obtina sirul format din octetii superiori ai cuvintelor inferioare din elementele sirului de dublucuvinte, care sunt multiplii de 10.  
    ;Exemplu: 
    ;Se da sirul de dublucuvinte:  
    ;s DD 12345678h, 1A2B3C4Dh, FE98DC76h  
    ;Sa se obtina sirul  
    ;d DB 3Ch, DCh. 
    
    s dd 12345678h, 1A2B3C4Dh, 0FE98DC76h 
    ;78 56 34 12 4D 3C 2B 1A 76 DC 98 FE
    ;0  1  2  3  4  5  6  7  8  9  10 11
    ls equ($-s)/4
    d times ls db 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov eax,0
        mov ebx,0
        mov ecx,0
        mov edx,0
        
        mov esi,0
        add esi,1
        mov edi,0
        mov ecx,ls
        Repeta:
            mov eax,0
            mov al,[s+esi]
            mov ah,0
            ;cbw
            mov bl,10
            idiv bl
            cmp ah,0
            JE adauga
            JNE nu_adauga
                adauga:
                    mov al,[s+esi]
                    mov [d+edi],al
                    add esi,4
                    inc edi
                    jmp final
                nu_adauga:
                    add esi,4
        final:
        LOOP Repeta
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
