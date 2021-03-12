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
    a dw 0000000001111111b
    b dw 0000111100000000b
    c dd 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
    ;the bits 0-4 of C have the value 1  
    ;the bits 5-11 of C are the same as the bits 0-6 of A 
    ;the bits 16-31 of C have the value 0000000001100101b 
    ;the bits 12-15 of C are the same as the bits 8-11 of B
    
    mov eax,0
    mov ebx,0
    mov ecx,0
    mov edx,0

    or dword[c],00000000000000000000000000011111b
    ;c=000000000000000000000000 0001 1111b
    push 00000000000000000000000000000000b
    push dword[c]
    
    mov ax,[a]
    and ax,0000000001111111b
    cwde
    shl eax,5
    or dword[c],eax
    ;c=00000000000000000000 a11 a10 a9 a8  a7 a6 a5 1 1111b
    push 00000000000000000000000000000000b
    push dword[c]
    
    or dword[c],00000000011001010000000000000000b
    ;c=0000000001100101 0000 a11 a10 a9 a8 a7 a6 a5 11111b
    push 00000000000000000000000000000000b
    push dword[c]
    
    mov ax,[b]
    and ax,0000111100000000b
    cwde
    shl eax,4
    or dword[c],eax
    ;c=0000000001100101 b8 b9 b10 b11 a11 a10 a9 a8 a7 a6 a5 11111b
    push 00000000000000000000000000000000b
    push dword[c]

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
