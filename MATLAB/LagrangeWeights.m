function w = LagrangeWeights(x_data)
%
% This function computes weights
%                                        1
%     w_j = -----------------------------------------------------------
%           (x_j-x_0)(x_j-x_1)...(x_j-x_{j-1})(x_j-x_{j+1})...(x_j-x_N)
% 
% which are used in the Lagrange form of an interpolating polynomial.
%
% Input : x_data - vector of length n = N+1 containing x_i interpolation
%                  data
%
% Output: w - vector of length n containing the weights
%
a = length(x_data);
w = ones(1, a);
for i=1:a
    for j=1:a
       if (j ~= i)
          w(1,i) = w(1,i)/(x_data(1,i) - x_data(1,j));
       end
    end
end
end