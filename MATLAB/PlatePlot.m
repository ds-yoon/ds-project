function [x] = PlatePlot(n) 
% 
% This function plots mesh and contour for the PlateSystem function. 
% 
% Input : n - n-by-n matrix 
%
% Output : mesh and contour plot with temperature value label.  
% 
[A, b] = PlateSystem(n); 
x = A\b;
T = reshape(x,n,n)';
mesh(T); figure();
[C,h] = contour(T);
clabel(C,h);