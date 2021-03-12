bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

                          
;Se dau 3 numere a - word, b - byte, c - doubleword. Sa se
;scrie codul care calculeaza valoarea expresiei (c-3)/b + a (fara semn/sau cu semn) in
;functie de caz.


; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
a dw 10
b db 5
c dd 13
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov eax,0
    mov ebx,0
    mov ecx,0
    mov edx,0
    mov eax,[c]
    sub eax,3
    ;eax=c-3
    mov ebx,eax
    ;ebx=(c-3)
    mov al,[b]
    cbw
    cwde
    xchg eax,ebx
    cdq
    idiv ebx
    mov ebx,eax
    mov ax,[a]
    cwde
    add ebx,eax
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
