bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
   import printf msvcrt.dll                       ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
;a dd 11110100b 
;b db 0FFh


;sir1 dw 0001001000110100b
;len_sir1 equ ($-sir1)/2
;sir2 times len_sir1 dw 0
;a db 0FFh

;a db 10h, 12h, 11h
;b db 98h, 0FFh, 0FFh


; a dq 0AABBCCDDh

; a db 3

; s dw 12h, -1
; t dd -2, 11b
; u dq 10, -3
; v db 'a, s, c', '3-2'

; format db '%x %x', 0
; a dq 0abcd123456abcdeh
; b dw 7890h
; c dw 1234h

; s dd 10,25,20,40
; len_s equ ($-s)/4

; a dw 3C4Dh
; b db 1Ah, 2Bh

 ; b dd 10
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;mov esi,sir1+1
        ;mov edi,sir2
        ;movsw
        ; mov eax,0
        ; mov edx,0
        ; mov ax,word[a+0]
        ; mov dx,word[a+2]
        
        ;Mov esi, a
        ;Mov edi, b
        ;STD ; right to left
        ;Cmpsb ; 11 cmp 56, edi = edi-1 and esi=esi-1

        ;mov eax,0
        ;lodsw
; mov dx,5
; cmp dx,[a]
; JE da
; JNE nu

; da:

; nu:
        ; mov eax, 0
        ; mov edx, 0
        
        ; mov dx, 1234h
        ; mov ax, 5678h
        ; push dx
        ; push ax
        ; pop eax
        
        ; stc
        ; mov eax,0
        ; sbb eax,0
        
        ; mov eax,0
        ; mov ebx,0
        ; mov bl,0FFh
        ; mov ax,bl

        ; mov al,2
        ; imul byte[a]
        
        ; mov bx,ax
        ; mov ax,13
        ; cwd
        ; idiv bx
        ; JO b
        
        ; JNO c
        ; b:
        ; c:
        
; push dword [a+2]
; push dword [b]
; push dword format
; call [printf]
; add esp, 4*3


; and BL, BL
; mov ECX, 4

; eticheta:

    ; stc
    ; rcr BL, 1h

; loop eticheta
; mov BH, BL


; mov esi,0
; mov ecx,len_s-1
; mov eax,0
; mov ebx,0
; mov edx,0
; mov bl,[s+esi]
; add esi,4

; mov al,[s+esi]
; eticheta:
    ; mov al,[s+esi]
    ; cmp bl,al
    ; JL rezolva
    ; JGE final
        ; rezolva:
        ; mov bl,al
        ; final:
        ; add esi,4
; loop eticheta

 ; mov ax,[a]

; mov eax,0
; sub eax,[b]
; cdq
; mov ebx,4
; idiv ebx
mov al,128
cbw

        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
