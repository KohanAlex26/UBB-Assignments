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
    
    ;8. Se da un sir de octeti S. Sa se construiasca un sir D1 care sa contina toate numerele pozitive si un sir D2 care sa contina toate numerele negative din S.  
    ;Exemplu: 
    ;S: 1, 3, -2, -5, 3, -8, 5, 0 
    ;D1: 1, 3, 3, 5, 0 
    ;D2: -2, -5, -8
    
    s db 1,3,-2,-5,3,-8,5,0
    ls equ($-s)
    d1 times ls db 0
    d2 times ls db 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov eax,0
        mov ebx,0
        mov ecx,0
        mov edx,0
            
        mov ecx,ls
        mov esi,0
        mov edi,0
        mov ebx,0
        Repeta:
            mov al,[s+esi]
            cmp al,0
            JGE pozitive
            JL negative
                pozitive:
                    mov [d1+edi],al
                    inc esi
                    inc edi
                    jmp final
                negative:
                    mov [d2+ebx],al
                    inc esi
                    inc ebx
        final:
        LOOP Repeta
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
