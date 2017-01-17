function [A, b] = PlateSystem(n)
%
% This function is to create a matrix Ax = b using Cholesky factorization 
% to calculate the temperature values of t(i) by taking average of 
% its four neighbors. (North, East, South, West) 
% 
% Input: n - n-by-n SPD matrix 
%
% Output: A - n^2 by n^2 matrix with the temperature
%         b - n^2 by 1 matrix with values of temperature. 
% 
E = zeros(n,1); 
W = zeros(n,1); 
z = linspace(0,2,n+2)'; 
f = sin((pi/2)*z).*exp(-z); 
N = f(2:n+1); S = N(n:-1:1); 
Z = zeros(n,1); 
b = zeros(n,1); 
A = full(gallery('poisson',n));   

N(1) = N(1)+W(1); 
N(n) = N(n)+E(1); 
x = (N(1:n));   

S(1) = S(1)+W(n); 
S(n) = S(n)+E(n); 
y = (S(1:n));   

m = zeros(n*(n-2),1);   

b = [x;m;y]