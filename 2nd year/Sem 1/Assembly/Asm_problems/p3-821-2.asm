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
a dw 10
b db 4
c dw 20
d db 15

; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov eax, 0
    mov ebx, 0
    mov ecx, 0
    mov edx, 0
    
    Mov al, [b]
    Mov ah, 0
    
    Mov bx, [a]
    
    Add ax,bx
    mov bx,3
    Sub bx,ax
    
    Mov cx,[c]
    Mov dl,[d]
    Mov dh,0
    
    Sub cx,dx
    Add cx,5
    Add bx,cx
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
