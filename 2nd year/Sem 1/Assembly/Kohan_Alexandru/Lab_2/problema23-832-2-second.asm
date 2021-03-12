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
a db 2
b db 2
c db 4
d db 6
e dw 10
x dq 5
; our code starts here
segment code use32 class=code
    start:
        ; ...
    ;(a*b-2*c*d)/(c-e)+x/a
    mov eax,0
    mov ebx,0
    mov ecx,0
    mov edx,0
    mov al,[a]
    imul byte[b];
    mov bx,ax
    ;bx=a*b
    mov al,2
    imul byte[c]
    mov cl,[d]
    mov ch,0
    imul cx
    ;dx:ax=2*c*d
    push dx
    push ax
    pop ecx
    mov eax,0
    mov ax,bx
    sub eax,ecx
    ;eax=(a*b-2*c*d)-double extended
    mov bx,0
    mov bl,[c]
    sub bx,[e]
    ;bx=c-e-word
    push eax
    pop ax
    pop dx
    idiv bx
     mov bx,ax 
     ;bx=(a*b-2*c*d)/(c-e)
     mov edx,dword[x+4]
     mov eax,dword[x+0]
     mov ecx,0
     mov cl,[a]
     idiv ecx
    ; eax=x/a
     mov ecx,eax
     mov ax,bx
     cwde
     add eax,ecx
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
