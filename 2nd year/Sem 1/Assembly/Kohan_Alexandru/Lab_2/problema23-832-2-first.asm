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
a db 5
b db 5
c db 2
d dw 10
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov eax,0
    mov ebx,0
    mov ecx,0
    mov edx,0
    
    ;(a+b)*3
    mov al,[a]
    add al,[b]
    mov bl,3
    imul bl
    mov cx,ax
    
    ;c*2
    mov al,[c]
    mov bl,2
    imul bl
    
    ;(a+b)*3-c*2
    sub cx,ax
    mov ax,cx
    cbw
    
    ;[(a+b)*3-c*2]+d
    add ax,[d]
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
