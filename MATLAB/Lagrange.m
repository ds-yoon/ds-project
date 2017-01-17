function [ MaxError ] = Lagrange(n)
%
% This function evaluate a Lagrange form that interpolates n points 
%
% Input :     n - number of Chebyshev points within the given interval
%
% Output : plot - The graph of the interpolating polynomial of true value, and
%                 approximated values. 
%      MaxError - Error between the true value x data vs approximated value
%                 x data
%
a = -1;
b = 1;
x = linspace(a, b, n);
f = (1+25*x.^2).^-1;
x1 = linspace(a,b, 201);
f1 = (1+25*x1.^2).^-1;
pvals = LagrangeEval(LagrangeWeights(x), x, f, x1);
hold on;
plot(x, f, 'ro') % data points
plot(x1, f1, 'k--') % true graph
plot(x1, pvals, 'b') % approximation graph
MaxError = max(abs(pvals - f1));
title('Interpolation using Lagrange');
legend('Interpolation points', 'True function', 'Interpolating poly');
hold off;
end