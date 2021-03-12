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
s db 10,20,21,35
len_s equ $-s
d times len_s db 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov ecx,len_s
    mov esi,0
    mov edi,0
    
    
    repeta:
    mov al,[s+esi]
    cbw
    mov bl,5
    idiv bl
    cmp ah,0
    JE adauga
    JNE nuadauga
    adauga:
    mov al,[s+esi]
    mov [d+edi],al
    inc edi
    add esi,2
    jmp final
    nuadauga:
    add esi,2
    final:
    cmp esi,len_s
    JL repeta
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
