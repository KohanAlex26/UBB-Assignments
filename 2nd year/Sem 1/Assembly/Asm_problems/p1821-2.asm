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

    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        ; instructiuni pt cod

;pas 1 (a+b)

; (a+b) -4 + (c-2) + a in reprezentarea unsign

mov eax, 0
mov ebx, 0
mov ecx, 0
mov edx, 0
 
        Mov al, [b]   ; al<- [b]
        Mov ah, 0      ; in ax avem valoarea lui b (b este extins la word)

        Mov bx, [a]   ; bx <- [a]

        Add ax, bx ; rez este in ax, ax=ax+bx=a+b

        ;pas 2 (a+b) -4

        Sub ax, 4 ; ax=ax-4= (a+b) -4
         ;pas 3  (c-2)

        Mov bx, [c]
        Sub bx, 2 ; bx=bx-2 = (c-2)

        ;Pasul 4(a+b) -4 + (c-2)
        Add ax, bx ; ax=ax+bx = (a+b) -4 + (c-2)

        ;Pasul 5( a+b) -4 + (c-2) + a

        Add ax, [a]; ax=ax+a = ( a+b) -4 + (c-2) + a


    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
